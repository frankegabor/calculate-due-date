package com.gfranke.duedate.validate;

import com.gfranke.duedate.util.DateUtil;
import com.gfranke.duedate.util.InvalidDateException;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SubmitDateValidatorTest {

    private static final LocalDateTime INPUT = LocalDateTime.of(2019, 10, 1, 10, 0);

    @Tested
    private SubmitDateValidator testedObject;

    @Test
    void validate_null_error() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(null);
        });
    }

    @Test
    void validate_weekend_error() {
        mockUpDateUtil(true, false);

        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(INPUT);
        });
    }

    @Test
    void validate_notWorkingHour_error() {
        mockUpDateUtil(false, true);

        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.validate(INPUT);
        });
    }

    @Test
    void validate_valid_noError() {
        mockUpDateUtil(false, false);

        Assertions.assertDoesNotThrow(() -> {
            testedObject.validate(INPUT);
        });
    }

    private void mockUpDateUtil(boolean weekend, boolean notWorkingHour) {
        new MockUp<DateUtil>() {

            @Mock
            public boolean isWeekend(LocalDateTime time) {
                return weekend;
            }

            @Mock
            public boolean isNotWorkingHour(LocalDateTime time) {
                return notWorkingHour;
            }
        };
    }
}