package com.markmcerlean.primechecker.validation;

import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import com.markmcerlean.primechecker.validation.PrimeCheckerValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrimeCheckerValidatorTests {
    private PrimeCheckerValidator validator;
    private PrimeCheckerModel primeCheckerModel;

    @Before
    public void setUp(){
        validator = new PrimeCheckerValidator();
        primeCheckerModel = new PrimeCheckerModel();
    }

    @Test
    public void testValueIsNull_Success(){
        primeCheckerModel.setValueToCheck(null);
        Assert.assertTrue(validator.valueIsNull(primeCheckerModel));
    }

    @Test
    public void TestValueContainsNoCharacters_Success(){
        primeCheckerModel.setValueToCheck("");
        Assert.assertTrue(validator.valueContainsNoCharacters(primeCheckerModel));
    }

    @Test
    public void TestValueContainsNonNumericCharacters_SuccessLetters(){
        primeCheckerModel.setValueToCheck("test");
        Assert.assertTrue(validator.valueContainsNonNumericCharacters(primeCheckerModel));
    }

    @Test
    public void TestValueContainsNonNumericCharacters_SuccessSpecialChars(){
        primeCheckerModel.setValueToCheck("!@#$%");
        Assert.assertTrue(validator.valueContainsNonNumericCharacters(primeCheckerModel));
    }

    @Test
    public void TestValueContainsSpace_Success(){
        primeCheckerModel.setValueToCheck("123 45");
        Assert.assertTrue(validator.valueContainsSpace(primeCheckerModel));
    }

    @Test
    public void TestValidateMethod_SuccessValidEntry(){
        primeCheckerModel.setValueToCheck("123");
        Assert.assertTrue(validator.validate(primeCheckerModel));
    }

    @Test
    public void TestValidateMethod_FailureWithLetters(){
        primeCheckerModel.setValueToCheck("123w");
        Assert.assertFalse(validator.validate(primeCheckerModel));
    }

    @Test
    public void TestValidateMethod_FailureWithSpace(){
        primeCheckerModel.setValueToCheck("123 ");
        Assert.assertFalse(validator.validate(primeCheckerModel));
    }

}
