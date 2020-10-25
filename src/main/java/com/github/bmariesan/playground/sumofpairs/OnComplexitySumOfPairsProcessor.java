package com.github.bmariesan.playground.sumofpairs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnComplexitySumOfPairsProcessor implements SumOfPairsProcessor {

    @Override
    public int findPairs(int desiredSum, List<Integer> arrayOfIntegers) {
        int arrayLength = arrayOfIntegers.size();
        Map<Integer, Integer> indexOfIntegerMap = new HashMap<>();
        for (int i = 0; i < arrayLength; i++) {
            indexOfIntegerMap.put(arrayOfIntegers.get(i), i);
        }

        int totalNumberOfPairs = 0;
        for (int i = 0; i < arrayLength - 1; i++) {
            Integer position = indexOfIntegerMap.get(desiredSum - arrayOfIntegers.get(i));
            if (position != null && i < position) {
                totalNumberOfPairs++;
            }
        }

        return totalNumberOfPairs;
    }
}
