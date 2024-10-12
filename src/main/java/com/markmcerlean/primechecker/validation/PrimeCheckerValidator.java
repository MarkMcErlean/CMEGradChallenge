package main.java.com.markmcerlean.primechecker.validation;

import main.java.com.markmcerlean.primechecker.models.PrimeCheckerModel;

public class PrimeCheckerValidator implements Validator<PrimeCheckerModel> {
    @Override
    public boolean validate(PrimeCheckerModel primeCheckerModel) {
        if (valueIsNull(primeCheckerModel)) return false;
        if (valueContainsNoCharacters(primeCheckerModel)) return false;
        if (valueContainsNonNumericCharacters(primeCheckerModel)) return false;
        if (valueContainsSpace(primeCheckerModel)) return false;
        return true;
    }

    protected boolean valueIsNull(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck() == null){
            primeCheckerModel.setMessage("Value To Check is null");
            System.out.println("Value To Check is null");
            return true;
        }
        return false;
    }

    protected boolean valueContainsNoCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().strip().isEmpty()){
            primeCheckerModel.setMessage("Value to check contains no characters");
            System.out.println("Value to check contains no characters");
            return true;
        }
        return false;
    }

    protected boolean valueContainsNonNumericCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().matches(".*/D*.")){
            primeCheckerModel.setMessage("Value to Check contains non numeric characters");
            System.out.println("Value to Check contains non-numeric characters");
            return true;
        }
        return false;
    }

    protected boolean valueContainsSpace(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().contains(" ")){
            primeCheckerModel.setMessage("value to check contains a space");
            System.out.println("value to check contains a space");
            return true;
        }
        return false;
    }
}
