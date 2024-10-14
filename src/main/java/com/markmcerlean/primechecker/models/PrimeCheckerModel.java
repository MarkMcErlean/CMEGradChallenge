package com.markmcerlean.primechecker.models;

public class PrimeCheckerModel implements Model{
    private String userName;
    private String valueToCheck;
//    private boolean hasPrimeNumbersInSequence;
    private String primeNumbersInSequence;
    private boolean isValid;
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getValueToCheck() {
        return valueToCheck;
    }

    public void setValueToCheck(String valueToCheck) {
        this.valueToCheck = valueToCheck;
    }

    public String getPrimeNumbersInSequence() {
        return primeNumbersInSequence;
    }

    public void setPrimeNumbersInSequence(String primeNumbersInSequence) {
        this.primeNumbersInSequence = primeNumbersInSequence;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "The Prime numbers in this sequence are: " + primeNumbersInSequence.replace(" ", ", ") ;
    }
}
