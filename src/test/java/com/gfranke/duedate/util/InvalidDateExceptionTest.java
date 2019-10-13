package com.gfranke.duedate.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InvalidDateExceptionTest {

    @Test
    void constructor_messagePassed_messageInitialized() {
        InvalidDateException testedObject = new InvalidDateException("error-message");

        Assertions.assertEquals("error-message", testedObject.getMessage());
    }
}