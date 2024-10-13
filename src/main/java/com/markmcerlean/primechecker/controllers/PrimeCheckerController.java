package com.markmcerlean.primechecker.controllers;

import com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import com.markmcerlean.primechecker.services.PrimeCheckerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PrimeCheckerController {
    private final PrimeCheckerService primeCheckerService;
    private final PrimeCheckerExceptionHandler primeCheckerExceptionHandler;
//    private final Logger logger = LogManager.getLogger(PrimeCheckerController.class);

    public PrimeCheckerController(PrimeCheckerService primeCheckerService, PrimeCheckerExceptionHandler primeCheckerExceptionHandler){
        this.primeCheckerService = primeCheckerService;
        this.primeCheckerExceptionHandler = primeCheckerExceptionHandler;
    }

    public PrimeCheckerModel processPrimeCheckerRequest(String username, String valueToCheck) throws IOException {

        try{
            return primeCheckerService.processRequest(username, valueToCheck);
        } catch (Exception e) { // change to FatalException later
            primeCheckerExceptionHandler.handleException(e);
            return null;
        }
    }

}
