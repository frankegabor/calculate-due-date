package com.gfranke.duedate.validate;

import com.gfranke.duedate.util.InvalidDateException;

public interface InputValidator<T> {

    void validate(T input) throws InvalidDateException;
}