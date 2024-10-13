package com.markmcerlean.primechecker.services;

import com.markmcerlean.primechecker.dao.Dao;
import com.markmcerlean.primechecker.exception.FatalException;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import com.markmcerlean.primechecker.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class PrimeCheckerService {
    private Map<String, PrimeCheckerModel> cache = new HashMap<>();
    private final Validator<PrimeCheckerModel> validator;
    private final Dao<PrimeCheckerModel> dao;
    private final Logger logger = LogManager.getLogger(PrimeCheckerService.class);

    public PrimeCheckerService(Validator<PrimeCheckerModel> validator, Dao<PrimeCheckerModel> dao) throws FatalException {
        this.validator = validator;
        this.dao = dao;
        setupCache(this.cache, this.dao);
    }

    public PrimeCheckerModel processRequest(String userName, String valueToCheck) throws FatalException {
        logger.info("Processing request with username [{}] and value [{}]", userName, valueToCheck);

        if (cache.containsKey(valueToCheck)){
            logger.info("Input [{}] found in cache", valueToCheck);
            PrimeCheckerModel cachedResult = cache.get(valueToCheck);
//            System.out.println(cachedResult.getPrimeNumbersInSequence());
            System.out.println(cachedResult.getPrimeNumbersInSequence());
            return cachedResult;
        }

        logger.info("Input [{}] not found in cache", valueToCheck);
        PrimeCheckerModel primeCheckerModel = parseToResponseModel(userName, valueToCheck);
        logger.info("Checking validation of [{}]", primeCheckerModel);

        primeCheckerModel.setValid(validator.validate(primeCheckerModel));

        if (primeCheckerModel.isValid()){
            logger.info("[{}] is valid", primeCheckerModel);
            List<String> combinations = generateCombinations(valueToCheck);
            Set<Integer> primeNumbersInSequence = getPrimeNumbersInSequence(combinations);
            primeCheckerModel.setPrimeNumbersInSequence(primeNumbersInSequence.toString().replace(",", ""));

            System.out.println(primeNumbersInSequence);
        }
        else {
            logger.info("[{}] is not valid", primeCheckerModel);
        }
        addToCache(primeCheckerModel);
        logger.info("[{}] added to cache", primeCheckerModel);
        writeToPersistence(primeCheckerModel);
        logger.info("Returning request [{}]", primeCheckerModel);
        return primeCheckerModel;
    }

    protected void writeToPersistence(PrimeCheckerModel primeCheckerModel) throws FatalException {
        dao.write(primeCheckerModel);
    }

    protected List<String> generateCombinations(String valueToCheck) {
        List<String> combinations = new ArrayList<>();
        for (int i = 0; i < valueToCheck.length(); i++) {
            for (int j = i + 1; j <= valueToCheck.length(); j++) {
                combinations.add(valueToCheck.substring(i, j));
            }
        }
        return combinations;
    }

    protected Set<Integer> getPrimeNumbersInSequence(List<String> combinations){
        Set<Integer> primeNumbersInSequence = new HashSet<>();
        for (String combination : combinations) {
            int number = Integer.parseInt(combination);
            if (isPrime(number)){
                primeNumbersInSequence.add(number);
            }
        }
        return primeNumbersInSequence;
    }

    protected boolean isPrime(int numberFromCombinations){
        if (numberFromCombinations <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numberFromCombinations); i++) {
            if (numberFromCombinations % i == 0) return false;
        }
        return true;
    }

    protected PrimeCheckerModel parseToResponseModel(String userName, String valueToCheck){
        PrimeCheckerModel primeCheckerModel = new PrimeCheckerModel();
        primeCheckerModel.setUserName(userName);
        logger.info("username set to [{}]", userName);
        primeCheckerModel.setValueToCheck(valueToCheck);
        logger.info("ValueToCheck set to [{}]", valueToCheck);
        logger.info("new responseModel is [{}]", primeCheckerModel);
        return primeCheckerModel;
    }

    protected void addToCache(PrimeCheckerModel responseModel){
        cache.put(responseModel.getValueToCheck(), responseModel);
    }

    protected void setupCache(Map<String, PrimeCheckerModel> cache, Dao<PrimeCheckerModel> dao) throws FatalException {
        logger.info("Populating cache");
        dao.readAll().forEach(m -> {cache.put(m.getValueToCheck(), m);});
    }
}
