package main.java.com.markmcerlean.primechecker.controllers;

import main.java.com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import main.java.com.markmcerlean.primechecker.models.PrimeCheckerModel;
import main.java.com.markmcerlean.primechecker.services.PrimeCheckerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeCheckerController {
    private final PrimeCheckerService primeCheckerService;
    private final PrimeCheckerExceptionHandler primeCheckerExceptionHandler;

    public PrimeCheckerController(PrimeCheckerService primeCheckerService, PrimeCheckerExceptionHandler primeCheckerExceptionHandler){
        this.primeCheckerService = primeCheckerService;
        this.primeCheckerExceptionHandler = primeCheckerExceptionHandler;
    }

    public PrimeCheckerModel processPrimeCheckerRequest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String username = reader.readLine();
        String valueToCheck = reader.readLine();
        try{
            return primeCheckerService.processRequest(username, valueToCheck);
        } catch (Exception e) { // change to FatalException later
            primeCheckerExceptionHandler.handleException(e);
            return null;
        }
    }

}
