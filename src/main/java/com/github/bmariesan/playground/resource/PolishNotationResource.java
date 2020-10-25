package com.github.bmariesan.playground.resource;

import com.github.bmariesan.playground.request.PolishNotationEvaluationRequest;
import com.github.bmariesan.playground.response.PolishNotationEvaluationResponse;
import com.github.bmariesan.playground.service.PolishNotationService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Parallel evaluation of expressions given in polish notation using parallelStream")
    @PostMapping("/parallel-evaluation")
    public ResponseEntity<List<PolishNotationEvaluationResponse>> calculateAllExpressionsInParallel(@RequestBody PolishNotationEvaluationRequest evaluationRequest) {
        return ResponseEntity.ok(polishNotationService.parallelParseAndCalculateExpressions(evaluationRequest));
    }

    @ApiOperation(value = "Sequential evaluation of expressions given in polish notation")
    @PostMapping("/sequential-evaluation")
    public ResponseEntity<List<PolishNotationEvaluationResponse>> calculateAllExpressionsSequentially(@RequestBody PolishNotationEvaluationRequest evaluationRequest) {
        return ResponseEntity.ok(polishNotationService.sequentialParseAndCalculateExpressions(evaluationRequest));
    }

}
