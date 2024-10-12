package main.java.com.markmcerlean.primechecker.models;

public class PrimeCheckerModel implements Model{
    private String userName;
    private String valueToCheck;
    private boolean hasPrimeNumbersInSequence;
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

    public boolean hasPrimeNumbersInSequence() {
        return hasPrimeNumbersInSequence;
    }

    public void setHasPrimeNumbersInSequence(boolean hasPrimeNumbersInSequence) {
        this.hasPrimeNumbersInSequence = hasPrimeNumbersInSequence;
    }

    public boolean isHasPrimeNumbersInSequence() {
        return hasPrimeNumbersInSequence;
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
}