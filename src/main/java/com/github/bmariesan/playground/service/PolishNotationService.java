package com.github.bmariesan.playground.service;

import com.github.bmariesan.playground.polishnotation.PolishNotationExpressionParser;
import com.github.bmariesan.playground.request.PolishNotationEvaluationRequest;
import com.github.bmariesan.playground.response.PolishNotationEvaluationResponse;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolishNotationService {

    private final PolishNotationExpressionParser polishNotationExpressionParser;

    public PolishNotationService(PolishNotationExpressionParser polishNotationExpressionParser) {
        this.polishNotationExpressionParser = polishNotationExpressionParser;
    }

    @Timed(value = "processing.parallel")
    public List<PolishNotationEvaluationResponse> parallelParseAndCalculateExpressions(PolishNotationEvaluationRequest evaluationRequest) {
        return evaluationRequest
                .getExpressions()
                .parallelStream()
                .map(this::evaluateAndMapResult)
                .collect(Collectors.toList());
    }

    @Timed(value = "processing.sequential")
    public List<PolishNotationEvaluationResponse> sequentialParseAndCalculateExpressions(PolishNotationEvaluationRequest evaluationRequest) {
        return evaluationRequest
                .getExpressions()
                .stream()
                .map(this::evaluateAndMapResult)
                .collect(Collectors.toList());
    }

    public PolishNotationEvaluationResponse evaluateAndMapResult(String expression) {
        return new PolishNotationEvaluationResponse(expression, polishNotationExpressionParser.parseAndCalculate(expression));
    }
}
