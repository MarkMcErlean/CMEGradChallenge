package com.markmcerlean.primechecker.validation;

import com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeCheckerValidator implements Validator<PrimeCheckerModel> {
    private final Logger logger = LogManager.getLogger(PrimeCheckerValidator.class);

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
            logger.info("[{}] is null", primeCheckerModel.getValueToCheck());
//            System.out.println("Value To Check is null");
            return true;
        }
        return false;
    }

    protected boolean valueContainsNoCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().strip().isEmpty()){
            primeCheckerModel.setMessage("Value to check contains no characters");
            logger.info("[{}] contains no characters", primeCheckerModel.getValueToCheck());
//            System.out.println("Value to check contains no characters");
            return true;
        }
        return false;
    }

    protected boolean valueContainsNonNumericCharacters(PrimeCheckerModel primeCheckerModel){
        if (!primeCheckerModel.getValueToCheck().matches("\\d+")){
            primeCheckerModel.setMessage("Value to Check contains non numeric characters");
            logger.info("[{}] contains non-numeric characters", primeCheckerModel.getValueToCheck());
//            System.out.println("Value to Check contains non-numeric characters");
            return true;
        }
        return false;
    }

    protected boolean valueContainsSpace(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().contains(" ")){
            primeCheckerModel.setMessage("value to check contains a space");
            logger.info("[{}] contains a space", primeCheckerModel.getValueToCheck());
//            System.out.println("value to check contains a space");
            return true;
        }
        return false;
    }
}
