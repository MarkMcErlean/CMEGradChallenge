package com.markmcerlean.primechecker;

import com.markmcerlean.primechecker.controllers.PrimeCheckerController;
import com.markmcerlean.primechecker.dao.Dao;
import com.markmcerlean.primechecker.dao.PrimeCheckerFileDao;
import com.markmcerlean.primechecker.exception.FatalException;
import com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import com.markmcerlean.primechecker.services.PrimeCheckerService;
import com.markmcerlean.primechecker.validation.PrimeCheckerValidator;
import com.markmcerlean.primechecker.validation.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, FatalException {
        Validator<PrimeCheckerModel> validator = new PrimeCheckerValidator();
        Dao<PrimeCheckerModel> dao = new PrimeCheckerFileDao("src/main/resources/PrimeCheckerData.csv");
        PrimeCheckerExceptionHandler primeCheckerExceptionHandler = new PrimeCheckerExceptionHandler();
        PrimeCheckerService primeCheckerService = new PrimeCheckerService(validator, dao);
        PrimeCheckerController primeCheckerController = new PrimeCheckerController(primeCheckerService, primeCheckerExceptionHandler);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean keepGoing = true;

        while (keepGoing){
            System.out.println("Enter a Username or enter quit to exit the program: ");
            String username = reader.readLine();
            if (username.equalsIgnoreCase("quit")){
                logger.info("Exiting the application");
                keepGoing = false;
            }
            else{
                System.out.println("Enter a sequence of integers: ");
                String valueToCheck = reader.readLine();
                primeCheckerController.processPrimeCheckerRequest(username, valueToCheck);
            }

        }
        //System.out.println("Hello world!");
    }
}