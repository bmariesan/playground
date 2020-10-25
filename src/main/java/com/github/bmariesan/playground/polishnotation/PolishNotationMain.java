package com.github.bmariesan.playground.polishnotation;

import io.micrometer.core.instrument.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolishNotationMain {

    public static void main(String[] args) {

        PolishNotationExpressionParser polishNotationExpressionParser = new PolishNotationExpressionParser();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Top stop type - exit");
        System.out.println("Please enter the expressions in polish notation on separate lines");

        List<String> polishExpressions = readInputData(scanner);

        System.out.println("Given expressions: " + polishExpressions.toString());

        long startTime = System.nanoTime();
        polishExpressions.forEach(expression -> {
            System.out.println(polishNotationExpressionParser.parseAndCalculate(expression));
        });
        long endTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in nanoseconds is: " + endTime);

    }

    public static List<String> readInputData(Scanner scanner) {
        List<String> polishExpressions = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if ("exit".equals(line)) {
                scanner.close();
                break;
            }

            if (StringUtils.isNotEmpty(line)) {
                polishExpressions.add(line);
            }
        }
        return polishExpressions;
    }
}
