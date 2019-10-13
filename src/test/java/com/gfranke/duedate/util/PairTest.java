package com.gfranke.duedate.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PairTest {

    private Pair<Integer, String> testedObject;

    @BeforeEach
    void setUp() {
        testedObject = new Pair<>(10, "test");
    }

    @Test
    void getKey_initializedValueReturns() {
        Assertions.assertEquals(10, testedObject.getKey());
    }

    @Test
    void getValue_initializedValueReturns() {
        Assertions.assertEquals("test", testedObject.getValue());
    }
}