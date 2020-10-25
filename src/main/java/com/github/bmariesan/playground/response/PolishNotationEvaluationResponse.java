package com.github.bmariesan.playground.response;

import java.util.Objects;

public class PolishNotationEvaluationResponse {
    private String expression;
    private String result;

    public PolishNotationEvaluationResponse() {
    }

    public PolishNotationEvaluationResponse(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PolishNotationEvaluationResponse{" +
                "expression='" + expression + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolishNotationEvaluationResponse that = (PolishNotationEvaluationResponse) o;
        return Objects.equals(expression, that.expression) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, result);
    }
}
