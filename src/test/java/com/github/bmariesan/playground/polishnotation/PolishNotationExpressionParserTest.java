package com.github.bmariesan.playground.polishnotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Polish notation parser")
class PolishNotationExpressionParserTest {


    @ParameterizedTest
    @MethodSource("validPolishExpressionsAndResults")
    @DisplayName("valid polish notations should be evaluated")
    public void testValidPolishExpressionsShouldBeEvaluated(String expression, String expectedResult) {
        // given
        PolishNotationExpressionParser polishNotationExpressionParser = new PolishNotationExpressionParser();

        // when
        String result = polishNotationExpressionParser.parseAndCalculate(expression);

        // then
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("invalidPolishExpressionsAndResults")
    @DisplayName("invalid polish notations should always return error")
    public void testInValidPolishExpressionsShouldReturnError(String expression, String expectedResult) {
        // given
        PolishNotationExpressionParser polishNotationExpressionParser = new PolishNotationExpressionParser();

        // when
        String result = polishNotationExpressionParser.parseAndCalculate(expression);

        // then
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> invalidPolishExpressionsAndResults() {
        return Stream.of(
                Arguments.of("", "error"),
                Arguments.of("/ 1 0", "error"),
                Arguments.of(null, "error"),
                Arguments.of("+ 1", "error"),
                Arguments.of("1 2", "error"),
                Arguments.of("/ + 1 2 0", "error"),
                Arguments.of("/ x 1 2 0", "error")
        );
    }

    private static Stream<Arguments> validPolishExpressionsAndResults() {
        return Stream.of(
                Arguments.of("+ + 0.5 1.5 * 4 10", "42.00"),
                Arguments.of("- 2e3 - 700 + 7 * 2 15", "1337.00"),
                Arguments.of("- -1.5 * 3.1415 / -7 -2", "-12.50"),
                Arguments.of("100500", "100500.00"),
                Arguments.of("/ 0 1", "0.00")
        );
    }
}