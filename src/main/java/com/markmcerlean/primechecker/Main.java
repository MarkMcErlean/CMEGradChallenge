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

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FatalException {
        Validator<PrimeCheckerModel> validator = new PrimeCheckerValidator();
        Dao<PrimeCheckerModel> dao = new PrimeCheckerFileDao();
        PrimeCheckerExceptionHandler primeCheckerExceptionHandler = new PrimeCheckerExceptionHandler();
        PrimeCheckerService primeCheckerService = new PrimeCheckerService(validator, dao);
        PrimeCheckerController primeCheckerController = new PrimeCheckerController(primeCheckerService, primeCheckerExceptionHandler);

        primeCheckerController.processPrimeCheckerRequest();
        //System.out.println("Hello world!");
    }
}