package main.java.com.markmcerlean.primechecker.services;

import main.java.com.markmcerlean.primechecker.dao.Dao;
import main.java.com.markmcerlean.primechecker.exception.FatalException;
import main.java.com.markmcerlean.primechecker.models.PrimeCheckerModel;
import main.java.com.markmcerlean.primechecker.validation.Validator;

import java.util.*;

public class PrimeCheckerService {
    private Map<String, PrimeCheckerModel> cache = new HashMap<>();
    private final Validator<PrimeCheckerModel> validator;
    private final Dao<PrimeCheckerModel> dao;

    public PrimeCheckerService(Validator<PrimeCheckerModel> validator, Dao<PrimeCheckerModel> dao) throws FatalException {
        this.validator = validator;
        this.dao = dao;
        setupCache(this.cache, this.dao);
        System.out.println(this.cache);
        System.out.println(this.dao.readAll());
    }

    public PrimeCheckerModel processRequest(String userName, String valueToCheck) throws FatalException {
        //log info here
        if (cache.containsKey(valueToCheck)){
            System.out.println("Input [" + valueToCheck + "] found in cache");

            PrimeCheckerModel cachedResult = cache.get(valueToCheck);
            System.out.println(cachedResult);
            return cache.get(valueToCheck);
        }

        System.out.println("Input [" + valueToCheck + "] not in cache");
        //log this ^
        PrimeCheckerModel primeCheckerModel = parseToResponseModel(userName, valueToCheck);
        primeCheckerModel.setValid(validator.validate(primeCheckerModel));

        if (primeCheckerModel.isValid()){
            List<String> combinations = generateCombinations(valueToCheck);
            List<Integer> primeNumbersInSequence = getPrimeNumbersInSequence(combinations);
            Collections.sort(primeNumbersInSequence);
            primeCheckerModel.setPrimeNumbersInSequence(primeNumbersInSequence.toString());
            System.out.println(primeNumbersInSequence);
        }
        addToCache(primeCheckerModel);
        writeToPersistence(primeCheckerModel);
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

    protected List<Integer> getPrimeNumbersInSequence(List<String> combinations){
        List<Integer> primeNumbersInSequence = new ArrayList<>();
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
        primeCheckerModel.setValueToCheck(valueToCheck);
        return primeCheckerModel;
    }

    protected void addToCache(PrimeCheckerModel responseModel){
        cache.put(responseModel.getValueToCheck(), responseModel);
        System.out.println("value to check is: " + responseModel.getValueToCheck());
        System.out.println("response model:" + responseModel);
    }

    protected void setupCache(Map<String, PrimeCheckerModel> cache, Dao<PrimeCheckerModel> dao) throws FatalException {
        System.out.println("Populating Cache");
        dao.readAll().forEach(m -> {cache.put(m.getValueToCheck(), m);});
    }
}
