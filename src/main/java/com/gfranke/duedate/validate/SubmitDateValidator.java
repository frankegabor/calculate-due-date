package com.gfranke.duedate.validate;

import com.gfranke.duedate.util.DateUtil;
import com.gfranke.duedate.util.InvalidDateException;

import java.time.LocalDateTime;

public class SubmitDateValidator implements InputValidator<LocalDateTime> {

    @Override
    public void validate(LocalDateTime input) throws InvalidDateException {
        if (null == input) {
            throw new InvalidDateException("Null submit time!");
        }
        if (DateUtil.isWeekend(input)) {
            throw new InvalidDateException("A problem can only be reported during working hours!");
        }
        if (DateUtil.isNotWorkingHour(input)) {
            throw new InvalidDateException("A problem can only be reported during working hours!");
        }
    }
}