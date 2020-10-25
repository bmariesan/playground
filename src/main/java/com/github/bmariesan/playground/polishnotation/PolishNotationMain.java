package com.github.bmariesan.playground.polishnotation;

public class PolishNotationMain {

    public static void main(String[] args) {

        PolishNotationExpressionParser polishNotationExpressionParser = new PolishNotationExpressionParser();

        System.out.println(polishNotationExpressionParser.parseAndCalculate("+ + 0.5 1.5 * 4 10"));
        System.out.println(polishNotationExpressionParser.parseAndCalculate("- 2e3 - 700 + 7 * 2 15"));
        System.out.println(polishNotationExpressionParser.parseAndCalculate("- -1.5 * 3.1415 / -7 -2"));
        System.out.println(polishNotationExpressionParser.parseAndCalculate("100500"));
        System.out.println(polishNotationExpressionParser.parseAndCalculate("1 2"));
        System.out.println(polishNotationExpressionParser.parseAndCalculate("+ 1"));

    }
}
