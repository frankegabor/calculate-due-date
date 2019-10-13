package com.gfranke.duedate.validate;

import com.gfranke.duedate.TurnaroundTime;
import com.gfranke.duedate.util.InvalidDateException;

public class TurnaroundTimeValidator implements InputValidator<TurnaroundTime> {

    @Override
    public void validate(TurnaroundTime input) throws InvalidDateException {
        if (null == input) {
            throw new InvalidDateException("Null turnaround time!");
        }
        if (null == input.getKey()) {
            throw new InvalidDateException("Null turnaround unit type!");
        }
        Long unitCounter = input.getValue();
        if (null == unitCounter || 0 >= unitCounter) {
            throw new InvalidDateException("Invalid turnaround unit counter!");
        }
    }
}