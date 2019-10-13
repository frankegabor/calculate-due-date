package com.gfranke.duedate.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

class DateUtilTest {

    @Test
    void isWeekend_saturday_true() {
        Assertions.assertTrue(DateUtil.isWeekend(LocalDateTime.of(2019, 10, 5, 10, 0)));
    }

    @Test
    void isWeekend_sunday_true() {
        Assertions.assertTrue(DateUtil.isWeekend(LocalDateTime.of(2019, 10, 6, 10, 0)));
    }

    @Test
    void isWeekend_tuesday_false() {
        Assertions.assertFalse(DateUtil.isWeekend(LocalDateTime.of(2019, 10, 1, 10, 0)));
    }

    @ParameterizedTest
    @ArgumentsSource(HourArgumentsProvider.class)
    void isNotWorkingHour_allScenario(int hour, int minute, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, DateUtil.isNotWorkingHour(LocalDateTime.of(2019, 10, 1, hour, minute)));
    }

    private static class HourArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(8, 0, true),
                    Arguments.of(8, 59, true),
                    Arguments.of(9, 0, false),
                    Arguments.of(12, 0, false),
                    Arguments.of(17, 0, false),
                    Arguments.of(17, 1, true),
                    Arguments.of(20, 0, true)
            );
        }
    }
}