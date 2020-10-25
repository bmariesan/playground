package com.github.bmariesan.playground.sumofpairs;

import io.micrometer.core.instrument.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfPairsMain {

    public static void main(String[] args) {
        SumOfPairsProcessor on2ComplexityProcessor = new On2ComplexitySumOfPairsProcessor();
        SumOfPairsProcessor onComplexityProcessor = new OnComplexitySumOfPairsProcessor();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Top stop type - exit");
        System.out.println("Please enter the desired sum: ");
        int desiredSum = scanner.nextInt();
        System.out.println("Please enter array elements one line at a time: ");

        List<Integer> arrayIntegers = readInputData(scanner);

        System.out.println("Given sum: " + desiredSum);
        System.out.println("Given array: " + arrayIntegers.toString());

        // First algo implementation uses O(n^2) complexity.
        long startTime = System.nanoTime();
        System.out.println("Inefficient algorithm result: " + on2ComplexityProcessor.processor(desiredSum, arrayIntegers));
        long on2EndTime = System.nanoTime() - startTime;
        System.out.println("Total execution time O(n^2) complexity in nanoseconds is:" + on2EndTime);

        startTime = System.nanoTime();

        // The algo above can be re-written and reduced to O(n) complexity by using a HashMap to store positioning of elements in array
        System.out.println("Optimised algorithm result: " + onComplexityProcessor.processor(desiredSum, arrayIntegers));
        long onEndTime = System.nanoTime() - startTime;
        System.out.println("Total execution time O(n) complexity in nanoseconds is: " + onEndTime);

        System.out.println("O(n) algorithm was faster by " + (on2EndTime - onEndTime) + " nanoseconds");
    }

    public static List<Integer> readInputData(Scanner scanner) {
        List<Integer> arrayIntegers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                scanner.close();
                break;
            }

            if (StringUtils.isNotEmpty(line)) {
                try {
                    arrayIntegers.add(Integer.parseInt(line));
                } catch (NumberFormatException exception) {
                    System.out.println("Non integer values are ignored: " + line);
                }
            }
        }
        return arrayIntegers;
    }
}
