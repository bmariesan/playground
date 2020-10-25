package com.github.bmariesan.playground.resource;

import com.github.bmariesan.playground.request.PolishNotationEvaluationRequest;
import com.github.bmariesan.playground.response.PolishNotationEvaluationResponse;
import com.github.bmariesan.playground.service.PolishNotationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/polish-notations")
public class PolishNotationResource {

    private final PolishNotationService polishNotationService;

    public PolishNotationResource(PolishNotationService polishNotationService) {
        this.polishNotationService = polishNotationService;
    }

    @PostMapping("/parallel-evaluation")
    public ResponseEntity<List<PolishNotationEvaluationResponse>> calculateAllExpressionsInParallel(@RequestBody PolishNotationEvaluationRequest evaluationRequest) {
        return ResponseEntity.ok(polishNotationService.parallelParseAndCalculateExpressions(evaluationRequest));
    }

    @PostMapping("/sequential-evaluation")
    public ResponseEntity<List<PolishNotationEvaluationResponse>> calculateAllExpressionsSequentially(@RequestBody PolishNotationEvaluationRequest evaluationRequest) {
        return ResponseEntity.ok(polishNotationService.sequentialParseAndCalculateExpressions(evaluationRequest));
    }

}
