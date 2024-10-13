package com.markmcerlean.primechecker.dao;

import com.markmcerlean.primechecker.dao.PrimeCheckerFileDao;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import org.junit.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class PrimeCheckerFileDaoTests{
    private final String TEST_FILENAME = "src/test/resources/TestPrimeCheckerData.csv";
    private PrimeCheckerFileDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new PrimeCheckerFileDao(TEST_FILENAME);
    }

    @Test
    public void testWriteSuccessWithGoodData() throws Exception {
        PrimeCheckerModel model = new PrimeCheckerModel();
        model.setUserName("testUser");
        model.setValueToCheck("12345");
        model.setPrimeNumbersInSequence("[2, 3, 5, 23]");
        model.setValid(true);
        model.setMessage("Test Message");

        dao.write(model);

        List<PrimeCheckerModel> models = dao.readAll();
        Assert.assertEquals(1, models.size());
    }

    //Test to see that results with failed validation are still stored for quicker processing
    @Test
    public void testWriteSuccessWithValidationError() throws Exception {
        PrimeCheckerModel model = new PrimeCheckerModel();
        model.setUserName("testUser");
        model.setValueToCheck("12345w");
        model.setPrimeNumbersInSequence("[2, 3, 5, 23]");
        model.setValid(true);
        model.setMessage("Test Message");

        dao.write(model);

        List<PrimeCheckerModel> models = dao.readAll();
        Assert.assertEquals(1, models.size());

        Assert.assertFalse(models.get(0).isValid());
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_FILENAME));
    }
}
