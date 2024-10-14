package com.markmcerlean.primechecker.dao;

import com.markmcerlean.primechecker.exception.FatalException;
import com.markmcerlean.primechecker.models.PrimeCheckerModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeCheckerFileDao implements Dao<PrimeCheckerModel> {
    private String filename = "src/main/resources/PrimeCheckerData.csv";
    private final Logger logger = LogManager.getLogger(PrimeCheckerFileDao.class);

    public PrimeCheckerFileDao(String filename){
        this.filename = filename;
    }

    @Override
    public PrimeCheckerModel read() {
        logger.info("Read method is not implemented for this class");
        return null;
    }

    @Override
    public void write(PrimeCheckerModel input) throws FatalException {
        StringBuilder sb = new StringBuilder();
        sb.append(input.getUserName()).append(",");
        sb.append(input.getValueToCheck()).append(",");
        sb.append(input.getPrimeNumbersInSequence()).append(",");
        sb.append(input.isValid()).append(",");
        sb.append(input.getMessage()).append(System.getProperty("line.separator"));
        logger.info("Persisting [{}] to the file [{}]", sb, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
            writer.append(sb.toString());
        } catch (IOException e){
            throw new FatalException(e);
        }
    }

    public PrimeCheckerModel parseLineToModel(String input){
        PrimeCheckerModel primeCheckerModel = new PrimeCheckerModel();
        String[] values = input.split(",");
        primeCheckerModel.setUserName(values[0]);
        primeCheckerModel.setValueToCheck(values[1]);
        primeCheckerModel.setPrimeNumbersInSequence(values[2]);
        primeCheckerModel.setValid(Boolean.parseBoolean(values[3]));
        primeCheckerModel.setMessage(values[4]);

        return primeCheckerModel;
    }

    @Override
    public List<PrimeCheckerModel> readAll() throws FatalException {
        List<PrimeCheckerModel> primeCheckerModels = new ArrayList<>();
        if (! new File(filename).exists()){
            logger.warn("WARNING - file does not exist");
            return primeCheckerModels;
        }
        Path path = Paths.get(filename);
        try {
            Files.readAllLines(path).forEach(line -> primeCheckerModels.add(parseLineToModel(line)));
        } catch (IOException e){
            throw new FatalException(e);
        }
        return primeCheckerModels;
    }
}
