package main.java.com.markmcerlean.primechecker;

import main.java.com.markmcerlean.primechecker.controllers.PrimeCheckerController;
import main.java.com.markmcerlean.primechecker.dao.Dao;
import main.java.com.markmcerlean.primechecker.dao.PrimeCheckerFileDao;
import main.java.com.markmcerlean.primechecker.exception.FatalException;
import main.java.com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import main.java.com.markmcerlean.primechecker.models.PrimeCheckerModel;
import main.java.com.markmcerlean.primechecker.services.PrimeCheckerService;
import main.java.com.markmcerlean.primechecker.validation.PrimeCheckerValidator;
import main.java.com.markmcerlean.primechecker.validation.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, FatalException {
        Validator<PrimeCheckerModel> validator = new PrimeCheckerValidator();
        Dao<PrimeCheckerModel> dao = new PrimeCheckerFileDao();
        PrimeCheckerExceptionHandler primeCheckerExceptionHandler = new PrimeCheckerExceptionHandler();
        PrimeCheckerService primeCheckerService = new PrimeCheckerService(validator, dao);
        PrimeCheckerController primeCheckerController = new PrimeCheckerController(primeCheckerService, primeCheckerExceptionHandler);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean keepGoing = true;
        while (keepGoing == true){
            System.out.println("Enter a Username or enter quit to exit the program: ");
            String username = reader.readLine();
            if (username.equalsIgnoreCase("quit")){
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