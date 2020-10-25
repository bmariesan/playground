package com.github.bmariesan.playground.service;

import com.github.bmariesan.playground.polishnotation.PolishNotationExpressionParser;
import com.github.bmariesan.playground.request.PolishNotationEvaluationRequest;
import com.github.bmariesan.playground.response.PolishNotationEvaluationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Polish notation service unit tests")
class PolishNotationServiceTest {

    @InjectMocks
    private PolishNotationService polishNotationService;

    @Mock
    private PolishNotationExpressionParser polishNotationExpressionParser;

    @Test
    @DisplayName("Parallel processing of polish notations should work on valid input")
    public void testParallelProcessingOfPolishNotationExpressionsShouldWorkOnValidInput() {
        // given
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = givenAValidPolishNotationEvaluationRequest();
        String expectedResult = aValidPolishNotationEvaluationResult();
        when(polishNotationExpressionParser.parseAndCalculate(anyString()))
                .thenReturn(expectedResult);

        // when
        List<PolishNotationEvaluationResponse> responses = polishNotationService.parallelParseAndCalculateExpressions(polishNotationEvaluationRequest);

        // then
        thenAssertValidInvocationAndResult(responses, expectedResult);
    }

    @Test
    @DisplayName("Sequential processing of polish notations should work on valid input")
    public void testSequentialProcessingOfPolishNotationExpressionsShouldWorkOnValidInput() {
        // given
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = givenAValidPolishNotationEvaluationRequest();
        String expectedResult = aValidPolishNotationEvaluationResult();
        when(polishNotationExpressionParser.parseAndCalculate(anyString()))
                .thenReturn(expectedResult);

        // when
        List<PolishNotationEvaluationResponse> responses = polishNotationService.sequentialParseAndCalculateExpressions(polishNotationEvaluationRequest);

        // then
        thenAssertValidInvocationAndResult(responses, expectedResult);
    }

    @Test
    @DisplayName("Parallel processing of polish notations should not work on empty input")
    public void testParallelProcessingOfPolishNotationExpressionsShouldNotWorkOnEmpty() {
        // given
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = givenAnEmptyInputPolishNotationEvaluationRequest();

        // when
        List<PolishNotationEvaluationResponse> responses = polishNotationService.parallelParseAndCalculateExpressions(polishNotationEvaluationRequest);

        // then
        thenAssertEmptyListInvocationAndResult(responses);
    }

    @Test
    @DisplayName("Sequential processing of polish notations should not work on empty input")
    public void testSequentialProcessingOfPolishNotationExpressionsShouldNotWorkOnEmpty() {
        // given
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = givenAnEmptyInputPolishNotationEvaluationRequest();

        // when
        List<PolishNotationEvaluationResponse> responses = polishNotationService.sequentialParseAndCalculateExpressions(polishNotationEvaluationRequest);

        // then
        thenAssertEmptyListInvocationAndResult(responses);
    }

    @Test
    @DisplayName("Parallel processing of polish notations show return error on invalid input")
    public void testParallelProcessingOfPolishNotationExpressionsShouldReturnErrorOnInvalidInput() {
        // given
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = givenAInValidPolishNotationEvaluationRequest();
        String expectedResult = anInValidPolishNotationEvaluationResult();
        when(polishNotationExpressionParser.parseAndCalculate(anyString()))
                .thenReturn(expectedResult);

        // when
        List<PolishNotationEvaluationResponse> responses = polishNotationService.parallelParseAndCalculateExpressions(polishNotationEvaluationRequest);

        // then
        thenAssertValidInvocationAndResult(responses, expectedResult);
    }

    @Test
    @DisplayName("Sequential processing of polish notations show return error on invalid input")
    public void testSequentialProcessingOfPolishNotationExpressionsShouldReturnErrorOnInvalidInput() {
        // given
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = givenAInValidPolishNotationEvaluationRequest();
        String expectedResult = anInValidPolishNotationEvaluationResult();
        when(polishNotationExpressionParser.parseAndCalculate(anyString()))
                .thenReturn(expectedResult);

        // when
        List<PolishNotationEvaluationResponse> responses = polishNotationService.sequentialParseAndCalculateExpressions(polishNotationEvaluationRequest);

        // then
        thenAssertValidInvocationAndResult(responses, expectedResult);
    }


    private void thenAssertValidInvocationAndResult(List<PolishNotationEvaluationResponse> responses, String expectedResult) {
        verify(polishNotationExpressionParser, times(1)).parseAndCalculate(anyString());
        assertEquals(1, responses.size());
        assertEquals(expectedResult, responses.get(0).getResult());
    }

    private void thenAssertEmptyListInvocationAndResult(List<PolishNotationEvaluationResponse> responses) {
        verify(polishNotationExpressionParser, times(0)).parseAndCalculate(anyString());
        assertEquals(0, responses.size());
    }

    private String aValidPolishNotationEvaluationResult() {
        return "2.00";
    }

    private String anInValidPolishNotationEvaluationResult() {
        return "error";
    }

    private PolishNotationEvaluationRequest givenAValidPolishNotationEvaluationRequest() {
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = new PolishNotationEvaluationRequest();
        polishNotationEvaluationRequest.setExpressions(List.of("+ 1 1"));
        return polishNotationEvaluationRequest;
    }

    private PolishNotationEvaluationRequest givenAInValidPolishNotationEvaluationRequest() {
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = new PolishNotationEvaluationRequest();
        polishNotationEvaluationRequest.setExpressions(List.of("/ 1 0"));
        return polishNotationEvaluationRequest;
    }

    private PolishNotationEvaluationRequest givenAnEmptyInputPolishNotationEvaluationRequest() {
        return new PolishNotationEvaluationRequest();
    }


}