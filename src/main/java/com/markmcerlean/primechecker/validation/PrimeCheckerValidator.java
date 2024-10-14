package com.markmcerlean.primechecker.validation;

import com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeCheckerValidator implements Validator<PrimeCheckerModel> {
    private final Logger logger = LogManager.getLogger(PrimeCheckerValidator.class);

    @Override
    public boolean validate(PrimeCheckerModel primeCheckerModel) {
//        if (userNameisNull(primeCheckerModel)) return false;
//        if (userNameContainsNoCharacters(primeCheckerModel)) return false;
//        if (userNameContainsNumericCharacters(primeCheckerModel)) return false;
//        if (userNameContainsSpace(primeCheckerModel)) return false;
//        if (userNameContainsSpecialCharacters(primeCheckerModel)) return false;
        if (valueIsNull(primeCheckerModel)) return false;
        if (valueContainsNoCharacters(primeCheckerModel)) return false;
        if (valueContainsNonNumericCharacters(primeCheckerModel)) return false;
        if (valueContainsSpace(primeCheckerModel)) return false;
        return true;
    }

    protected boolean userNameisNull(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getUserName() == null){
            primeCheckerModel.setMessage("Username is null");
            logger.info("username [{}] is null", primeCheckerModel.getUserName());
            return true;
        }
        return false;
    }

    protected boolean userNameContainsNoCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getUserName().strip().isEmpty()){
            primeCheckerModel.setMessage("userName contains no characters");
            logger.info("username [{}] contains no characters", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean userNameContainsNumericCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getUserName().matches("\\d+")){
            primeCheckerModel.setMessage("userName contains non numeric characters");
            logger.info("userName [{}] contains numeric characters", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean userNameContainsSpace(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getUserName().contains(" ")){
            primeCheckerModel.setMessage("Username contains a space");
            logger.info("userName [{}] contains a space", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean userNameContainsSpecialCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getUserName().matches("[^a-zA-Z0-9]")){
            primeCheckerModel.setMessage("Value to Check contains non numeric characters");
            logger.info("userName [{}] contains special characters", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean valueIsNull(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck() == null){
            primeCheckerModel.setMessage("Value To Check is null");
            logger.info("valueToCheck [{}] is null", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean valueContainsNoCharacters(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().strip().isEmpty()){
            primeCheckerModel.setMessage("Value to check contains no characters");
            logger.info("valueToCheck [{}] contains no characters", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean valueContainsNonNumericCharacters(PrimeCheckerModel primeCheckerModel){
        if (!primeCheckerModel.getValueToCheck().matches("\\d+")){
            primeCheckerModel.setMessage("Value to Check contains non numeric characters");
            logger.info("valueToCheck [{}] contains non-numeric characters", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }

    protected boolean valueContainsSpace(PrimeCheckerModel primeCheckerModel){
        if (primeCheckerModel.getValueToCheck().contains(" ")){
            primeCheckerModel.setMessage("value to check contains a space");
            logger.info("valueToCheck [{}] contains a space", primeCheckerModel.getValueToCheck());
            return true;
        }
        return false;
    }
}
