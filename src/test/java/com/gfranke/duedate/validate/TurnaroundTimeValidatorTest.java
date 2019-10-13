package com.gfranke.duedate.validate;

import com.gfranke.duedate.TurnaroundTime;
import com.gfranke.duedate.util.InvalidDateException;
import com.gfranke.duedate.util.TimeUnit;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TurnaroundTimeValidatorTest {

    @Tested
    private TurnaroundTimeValidator testedObject;

    @Test
    void validate_null_error() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(null);
        });
    }

    @Test
    void validate_nullTimeUnit_error() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(new TurnaroundTime(null, 10L));
        });
    }

    @Test
    void validate_nullUnitCounter_error() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(new TurnaroundTime(TimeUnit.HOUR, null));
        });
    }

    @Test
    void validate_zeroUnitCounter_error() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(new TurnaroundTime(TimeUnit.HOUR, 0L));
        });
    }

    @Test
    void validate_negativeUnitCounter_error() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(new TurnaroundTime(TimeUnit.HOUR, -1L));
        });
    }

    @Test
    void validate_valid_noError() {
        Assertions.assertDoesNotThrow(() -> {
            testedObject.validate(new TurnaroundTime(TimeUnit.HOUR, 10L));
        });
    }
}