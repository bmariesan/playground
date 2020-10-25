package com.github.bmariesan.playground.polishnotation;

import com.github.bmariesan.playground.polishnotation.operator.*;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class PolishNotationExpressionParser {

    private static final List<String> OPERATORS = List.of("+", "-", "*", "/");
    private static final Map<String, Operator> operatorStrategy = Map.of(
            "+", new Addition(),
            "-", new Subtraction(),
            "*", new Multiplication(),
            "/", new Division());

    @Timed(value = "parsing.duration")
    public String parseAndCalculate(String expressionLine) {
        if (StringUtils.isEmpty(expressionLine)) {
            return "error";
        }

        List<String> expression = Arrays.asList(expressionLine.trim().split(" "));
        Collections.reverse(expression);

        Stack<Double> operandStack = new Stack<>();
        for (String element : expression) {
            if (operandStack.size() < 2 && isOperator(element)) {
                return "error";
            }

            if (isOperator(element) && operandStack.size() > 0) {
                Operator operator = operatorStrategy.get(element);
                Double leftSideOperand = operandStack.pop();
                Double rightSideOperand = operandStack.pop();
                if (isDivisionByZero(operator, rightSideOperand)) {
                    return "error";
                }
                operandStack.push(operator.calculate(leftSideOperand, rightSideOperand));
            } else {
                operandStack.push(Double.valueOf(element));
            }
        }

        if (operandStack.size() == 1) {
            return String.format("%.2f", operandStack.pop());
        }

        return "error";
    }

    private boolean isDivisionByZero(Operator operator, Double rightSideOperand) {
        return operator instanceof Division && rightSideOperand == 0;
    }

    private boolean isOperator(String element) {
        return OPERATORS.contains(element);
    }
}
