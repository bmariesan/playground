package com.github.bmariesan.playground.polishnotation.operator;

public class Division implements Operator {
    @Override
    public double calculate(double leftSideOperand, double rightSideOperand) {
        return leftSideOperand / rightSideOperand;
    }
}
