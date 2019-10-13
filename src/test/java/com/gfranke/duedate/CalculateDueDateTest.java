package com.gfranke.duedate;

import com.gfranke.duedate.util.InvalidDateException;
import com.gfranke.duedate.util.TimeUnit;
import com.gfranke.duedate.validate.SubmitDateValidator;
import com.gfranke.duedate.validate.TurnaroundTimeValidator;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

class CalculateDueDateTest {

    private static final LocalDateTime INPUT = LocalDateTime.of(2019, 10, 1, 12, 0);

    @Tested
    private CalculateDueDate testedObject;

    @Test
    void calculate_invalidBySubmitTimeValidator_error(@Mocked SubmitDateValidator validator) throws InvalidDateException {
        new Expectations() {{
            validator.validate(INPUT);
            result = new InvalidDateException("");
        }};

        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.calculate(INPUT, null);
        });
    }

    @Test
    void calculate_invalidByTurnaroundTimeValidator_error(@Mocked TurnaroundTimeValidator validator) throws InvalidDateException {
        TurnaroundTime inputTurnaroundTime = new TurnaroundTime(TimeUnit.HOUR, 1L);
        new Expectations() {{
            validator.validate(inputTurnaroundTime);
            result = new InvalidDateException("");
        }};

        Assertions.assertThrows(InvalidDateException.class, () -> {
            testedObject.calculate(INPUT, inputTurnaroundTime);
        });
    }

    @ParameterizedTest
    @ArgumentsSource(CalculationArgumentsProvider.class)
    void calculate_validCalculations(LocalDateTime testInput, TimeUnit timeUnit, long counter, LocalDateTime expectedResult) throws InvalidDateException {
        LocalDateTime result = testedObject.calculate(testInput, new TurnaroundTime(timeUnit, counter));

        Assertions.assertEquals(expectedResult, result);
    }

    private static class CalculationArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(INPUT, TimeUnit.WEEK, 1L, LocalDateTime.of(2019, 10, 8, 12, 0)),
                    Arguments.of(INPUT, TimeUnit.WEEK, 2L, LocalDateTime.of(2019, 10, 15, 12, 0)),
                    Arguments.of(INPUT, TimeUnit.DAY, 2L, LocalDateTime.of(2019, 10, 3, 12, 0)),
                    Arguments.of(INPUT, TimeUnit.DAY, 4L, LocalDateTime.of(2019, 10, 7, 12, 0)),
                    Arguments.of(INPUT, TimeUnit.DAY, 10L, LocalDateTime.of(2019, 10, 15, 12, 0)),
                    Arguments.of(INPUT, TimeUnit.HOUR, 2L, LocalDateTime.of(2019, 10, 1, 14, 0)),
                    Arguments.of(INPUT, TimeUnit.HOUR, 6L, LocalDateTime.of(2019, 10, 2, 10, 0)),
                    Arguments.of(INPUT, TimeUnit.HOUR, 10L, LocalDateTime.of(2019, 10, 2, 14, 0)),
                    Arguments.of(INPUT, TimeUnit.HOUR, 34L, LocalDateTime.of(2019, 10, 7, 14, 0)),
                    Arguments.of(INPUT.plusDays(3L), TimeUnit.HOUR, 6L, LocalDateTime.of(2019, 10, 7, 10, 0))
            );
        }
    }
}