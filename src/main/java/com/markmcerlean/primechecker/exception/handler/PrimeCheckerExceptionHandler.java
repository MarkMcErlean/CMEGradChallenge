package main.java.com.markmcerlean.primechecker.exception.handler;

import main.java.com.markmcerlean.primechecker.exception.FatalException;


public class PrimeCheckerExceptionHandler {
    public void handleException(Exception e){
        logMessageEncounter(e);
        if (e instanceof FatalException){
            shutdown();
        }
    }
    private void shutdown(){
        System.exit(-1);
    }

    protected void logMessageEncounter(Exception e){
        return;
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
