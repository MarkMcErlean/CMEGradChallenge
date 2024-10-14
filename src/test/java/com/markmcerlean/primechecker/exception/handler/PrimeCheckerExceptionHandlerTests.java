package com.markmcerlean.primechecker.exception.handler;

import com.markmcerlean.primechecker.exception.handler.PrimeCheckerExceptionHandler;
import com.markmcerlean.primechecker.exception.FatalException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrimeCheckerExceptionHandlerTests {
    private PrimeCheckerExceptionHandler exceptionHandler;

    @Before
    public void setUp(){
        exceptionHandler = new PrimeCheckerExceptionHandler();
    }

    @Test
    public void testFindRootException_Success(){
        Exception rootCause = new Exception("Root Cause");

        String rootMessage = exceptionHandler.findRootException(rootCause);
        Assert.assertEquals("Root Cause", rootMessage);
    }

    @Test
    public void testFindRootExceptionInNestedException_Success(){
        Exception rootCause = new Exception("Root Cause");
        Exception nestedException = new Exception("Nested", rootCause);
        Exception TopException = new Exception("Top", nestedException);

        String rootMessage = exceptionHandler.findRootException(TopException);
        Assert.assertEquals("Root Cause", rootMessage);
    }
}
