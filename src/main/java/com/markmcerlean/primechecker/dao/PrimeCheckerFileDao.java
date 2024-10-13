package main.java.com.markmcerlean.primechecker.dao;

import main.java.com.markmcerlean.primechecker.exception.FatalException;
import main.java.com.markmcerlean.primechecker.models.PrimeCheckerModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PrimeCheckerFileDao implements Dao<PrimeCheckerModel> {
    private final String filename = "src/main/resources/PrimeCheckerData.csv";
    @Override
    public PrimeCheckerModel read() {
        System.out.println("Read method is not implemented for this class");
        return null;
    }

    @Override
    public void write(PrimeCheckerModel input) throws FatalException {
        StringBuilder sb = new StringBuilder();
        sb.append(input.getUserName()).append(",");
        sb.append(input.getValueToCheck()).append(",");
//        sb.append(input.hasPrimeNumbersInSequence()).append(",");
        sb.append(input.getPrimeNumbersInSequence()).append(",");
        sb.append(input.isValid()).append(",");
        sb.append(input.getMessage()).append(System.getProperty("line.separator"));
        //log ("persisting xyz to file filename)
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
//        primeCheckerModel.setHasPrimeNumbersInSequence(Boolean.parseBoolean(values[2]));
        primeCheckerModel.setValid(Boolean.parseBoolean(values[2]));
        primeCheckerModel.setMessage(values[3]);

        return primeCheckerModel;
    }

    @Override
    public List<PrimeCheckerModel> readAll() throws FatalException {
        List<PrimeCheckerModel> primeCheckerModels = new ArrayList<>();
        if (! new File(filename).exists()){
            System.out.println("WARNING - file does not exist");
            //log this ^
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
