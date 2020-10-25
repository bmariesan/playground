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

        List<String> expression = Arrays.asList(expressionLine.split(" "));
        Collections.reverse(expression);

        Stack<Double> operandStack = new Stack<>();
        for (String element : expression) {
            if (operandStack.size() < 2 && isOperator(element)) {
                return "error";
            }

            if (isOperator(element) && operandStack.size() > 0) {
                Operator operator = operatorStrategy.get(element);
                operandStack.push(operator.calculate(operandStack.pop(), operandStack.pop()));
            } else {
                operandStack.push(Double.valueOf(element));
            }
        }

        if (operandStack.size() == 1) {
            return String.format("%.2f", operandStack.pop());
        }

        return "error";
    }

    private boolean isOperator(String element) {
        return OPERATORS.contains(element);
    }
}
