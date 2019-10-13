package com.gfranke.duedate;

import com.gfranke.duedate.util.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnaroundTimeTest {

    private TurnaroundTime testedObject;

    @BeforeEach
    void setUp() {
        testedObject = new TurnaroundTime(TimeUnit.HOUR, 10L);
    }

    @Test
    void getKey_initializedValueReturns() {
        Assertions.assertEquals(TimeUnit.HOUR, testedObject.getKey());
    }

    @Test
    void getValue_initializedValueReturns() {
        Assertions.assertEquals(10L, testedObject.getValue());
    }
}