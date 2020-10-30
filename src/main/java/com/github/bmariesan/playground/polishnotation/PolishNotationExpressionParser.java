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
    private static final String ERROR_IN_EXPRESSION_EVALUATION = "error";
    private static final String DOUBLE_DECIMAL_FORMAT = "%.2f";
    private static final String EMPTY_SPACE_DELIMITER = " ";

    @Timed(value = "parsing.duration")
    public String parseAndCalculate(String expressionLine) {
        if (StringUtils.isEmpty(expressionLine)) {
            return ERROR_IN_EXPRESSION_EVALUATION;
        }

        List<String> expression = Arrays.asList(expressionLine.trim().split(EMPTY_SPACE_DELIMITER));

        if (expression.size() > 100000) {
            return ERROR_IN_EXPRESSION_EVALUATION;
        }

        Collections.reverse(expression);

        Stack<Double> operandStack = new Stack<>();
        for (String element : expression) {
            if (operandStack.size() < 2 && isOperator(element)) {
                return ERROR_IN_EXPRESSION_EVALUATION;
            }

            if (isOperator(element) && operandStack.size() > 0) {
                Operator operator = operatorStrategy.get(element);
                Double leftSideOperand = operandStack.pop();
                Double rightSideOperand = operandStack.pop();
                if (isDivisionByZero(operator, rightSideOperand)) {
                    return ERROR_IN_EXPRESSION_EVALUATION;
                }
                operandStack.push(operator.calculate(leftSideOperand, rightSideOperand));
            } else {
                try {
                    operandStack.push(Double.valueOf(element));
                } catch (NumberFormatException e) {
                    return ERROR_IN_EXPRESSION_EVALUATION;
                }
            }
        }

        if (operandStack.size() == 1) {
            return String.format(DOUBLE_DECIMAL_FORMAT, operandStack.pop());
        }

        return ERROR_IN_EXPRESSION_EVALUATION;
    }

    private boolean isDivisionByZero(Operator operator, Double rightSideOperand) {
        return operator instanceof Division && rightSideOperand == 0;
    }

    private boolean isOperator(String element) {
        return OPERATORS.contains(element);
    }
}
