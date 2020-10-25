package com.github.bmariesan.playground.sumofpairs;

import java.util.List;

public class On2ComplexitySumOfPairsProcessor implements SumOfPairsProcessor {

    @Override
    public int findPairs(int desiredSum, List<Integer> arrayOfIntegers) {
        int arrayLength = arrayOfIntegers.size();
        int totalNumberOfPairs = 0;
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (arrayOfIntegers.get(i) + arrayOfIntegers.get(j) == desiredSum) {
                    totalNumberOfPairs++;
                }
            }
        }

        return totalNumberOfPairs;
    }
}
