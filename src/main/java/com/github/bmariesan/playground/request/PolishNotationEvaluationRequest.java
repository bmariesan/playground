package com.github.bmariesan.playground.request;

import java.util.ArrayList;
import java.util.List;

public class PolishNotationEvaluationRequest {

    private List<String> expressions = new ArrayList<>();

    public List<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }
}
