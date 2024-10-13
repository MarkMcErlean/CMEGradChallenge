package com.markmcerlean.primechecker.validation;

import com.markmcerlean.primechecker.models.Model;

public interface Validator <T extends Model>{
    boolean validate(T input);
}
