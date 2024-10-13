package com.markmcerlean.primechecker.exception.handler;

import com.markmcerlean.primechecker.exception.FatalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PrimeCheckerExceptionHandler {
    private final Logger logger = LogManager.getLogger(PrimeCheckerExceptionHandler.class);

    public void handleException(Exception e){
        logMessageEncounter(e);
        if (e instanceof FatalException){
            shutdown();
        }
    }
    private void shutdown(){
        logger.info("Exiting the application");
        System.exit(-1);
    }

    protected void logMessageEncounter(Exception e){
        logger.info("Exception encountered: [{}]. Root exception: [{}]", e.getMessage(), findRootException(e));
    }

    private String findRootException(Throwable t){
        Throwable cause = null;
        Throwable result = t;

        while (null != (cause = result.getCause()) && (result != cause)){
            result = cause;
        }
        return result.getMessage();
    }
}
