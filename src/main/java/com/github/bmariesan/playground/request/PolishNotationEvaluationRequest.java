package com.github.bmariesan.playground.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PolishNotationEvaluationRequest {

    private List<String> expressions = new ArrayList<>();

    public List<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "PolishNotationEvaluationRequest{" +
                "expressions=" + expressions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolishNotationEvaluationRequest that = (PolishNotationEvaluationRequest) o;
        return Objects.equals(expressions, that.expressions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressions);
    }
}
