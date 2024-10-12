package main.java.com.markmcerlean.primechecker.validation;

import main.java.com.markmcerlean.primechecker.models.Model;

public interface Validator <T extends Model>{
    boolean validate(T input);
}
