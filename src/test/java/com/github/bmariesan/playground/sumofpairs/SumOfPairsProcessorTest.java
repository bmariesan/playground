package com.github.bmariesan.playground.sumofpairs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Sum of pairs ")
class SumOfPairsProcessorTest {

    @ParameterizedTest
    @MethodSource("invalidInputArraySize")
    @DisplayName("O(n^2) processor should not work with array size lesser than 2")
    void testOn2ProcessorInvalidNumberOfElementsInArray(List<Integer> input) {
        // given
        SumOfPairsProcessor processor = new On2ComplexitySumOfPairsProcessor();
        // when
        int result = processor.processor(5, input);
        // then
        assertEquals(0, result);
    }

    @ParameterizedTest
    @MethodSource("invalidInputArraySize")
    @DisplayName("O(n) processor should not work with array size lesser than 2")
    void testOnProcessorInvalidNumberOfElementsInArray(List<Integer> input) {
        // given
        SumOfPairsProcessor processor = new OnComplexitySumOfPairsProcessor();
        // when
        int result = processor.processor(5, input);
        // then
        assertEquals(0, result);
    }

    @ParameterizedTest
    @MethodSource("arraysWithDuplicateElements")
    @DisplayName("O(n^2) processor should not work with array with duplicate elements")
    void testOn2ProcessorInputArrayContainsDuplicateElements(List<Integer> input) {
        // given
        SumOfPairsProcessor processor = new On2ComplexitySumOfPairsProcessor();
        // when
        int result = processor.processor(5, input);
        // then
        assertEquals(0, result);
    }

    @ParameterizedTest
    @MethodSource("arraysWithDuplicateElements")
    @DisplayName("O(n) processor should not work with array with duplicate elements")
    void testOnProcessorInputArrayContainsDuplicateElements(List<Integer> input) {
        // given
        SumOfPairsProcessor processor = new OnComplexitySumOfPairsProcessor();
        // when
        int result = processor.processor(5, input);
        // then
        assertEquals(0, result);
    }

    @ParameterizedTest
    @MethodSource("validSumExpectedResultAndInputArray")
    @DisplayName("O(n^2) processor should work with valid input data given as signed integers")
    void testOn2ProcessorValidInputArrayAndSumWithSignedIntegers(int desiredSum, int expectedPairs, List<Integer> input) {
        // given
        SumOfPairsProcessor processor = new On2ComplexitySumOfPairsProcessor();
        // when
        int result = processor.processor(desiredSum, input);
        // then
        assertEquals(expectedPairs, result);
    }

    @ParameterizedTest
    @MethodSource("validSumExpectedResultAndInputArray")
    @DisplayName("O(n) processor should work with valid input data given as signed integers")
    void testOnProcessorValidInputArrayAndSumWithSignedIntegers(int desiredSum, int expectedPairs, List<Integer> input) {
        // given
        SumOfPairsProcessor processor = new OnComplexitySumOfPairsProcessor();
        // when
        int result = processor.processor(desiredSum, input);
        // then
        assertEquals(expectedPairs, result);
    }

    private static Stream<Arguments> invalidInputArraySize() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(Collections.singletonList(5))
        );
    }

    private static Stream<Arguments> arraysWithDuplicateElements() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 1)),
                Arguments.of(List.of(1, 1, 1, 1)),
                Arguments.of(List.of(-1, 1, 2, -1)),
                Arguments.of(List.of(0, 1, 0, -1))
        );
    }

    private static Stream<Arguments> validSumExpectedResultAndInputArray() {
        return Stream.of(
                Arguments.of(-5, 2, List.of(-15, 10, 20, 5, -10)),
                Arguments.of(0, 1, List.of(0, 5, -5, 10)),
                Arguments.of(5, 3, List.of(0, 5, -5, 10, -15, 20))
        );
    }
}