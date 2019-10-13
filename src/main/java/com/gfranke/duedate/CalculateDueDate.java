package com.gfranke.duedate;

import com.gfranke.duedate.util.DateUtil;
import com.gfranke.duedate.util.InvalidDateException;
import com.gfranke.duedate.util.TimeUnit;
import com.gfranke.duedate.validate.InputValidator;
import com.gfranke.duedate.validate.SubmitDateValidator;
import com.gfranke.duedate.validate.TurnaroundTimeValidator;

import java.time.LocalDateTime;

public class CalculateDueDate {

    private final InputValidator<LocalDateTime> submitTimeValidator;
    private final InputValidator<TurnaroundTime> turnaroundTimeValidator;

    public CalculateDueDate() {
        this.submitTimeValidator = new SubmitDateValidator();
        turnaroundTimeValidator = new TurnaroundTimeValidator();
    }

    public LocalDateTime calculate(LocalDateTime submitTime, TurnaroundTime turnaroundTime) throws InvalidDateException {
        submitTimeValidator.validate(submitTime);
        turnaroundTimeValidator.validate(turnaroundTime);
        TimeUnit unit = turnaroundTime.getKey();
        if (unit == TimeUnit.WEEK) {
            return calculateWeek(submitTime, turnaroundTime.getValue());
        }
        if (unit == TimeUnit.DAY) {
            LocalDateTime time = calculateDay(submitTime, turnaroundTime.getValue() % 5);
            return calculateWeek(time, turnaroundTime.getValue() / 5);
        }
        if (unit == TimeUnit.HOUR) {
            LocalDateTime time = calculateHour(submitTime, turnaroundTime.getValue() % 8);
            return calculateDay(time, turnaroundTime.getValue() / 8);
        }
        throw new InvalidDateException("Unexpected time unit!");
    }

    private LocalDateTime calculateWeek(LocalDateTime submitTime, long weeks) {
        return submitTime.plusWeeks(weeks);
    }

    private LocalDateTime calculateDay(LocalDateTime submitTime, long days) {
        LocalDateTime dueTime = submitTime.plusDays(days);
        if (DateUtil.isWeekend(dueTime)) {
            return dueTime.plusDays(2);
        }
        return dueTime;
    }

    private LocalDateTime calculateHour(LocalDateTime submitTime, long hours) {
        LocalDateTime dueTime = submitTime.plusHours(hours);
        if (DateUtil.isNotWorkingHour(dueTime)) {
            dueTime = dueTime.plusHours(16);
        }
        if (DateUtil.isWeekend(dueTime)) {
            dueTime = dueTime.plusDays(2);
        }
        return dueTime;
    }
}