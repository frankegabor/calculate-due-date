package com.gfranke.duedate;

import com.gfranke.duedate.util.Pair;
import com.gfranke.duedate.util.TimeUnit;

public class TurnaroundTime extends Pair<TimeUnit, Long> {

    public TurnaroundTime(TimeUnit key, Long value) {
        super(key, value);
    }
}