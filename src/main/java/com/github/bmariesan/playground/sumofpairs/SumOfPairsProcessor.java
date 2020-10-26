package com.github.bmariesan.playground.sumofpairs;

import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface SumOfPairsProcessor {

    default int processor(int desiredSum, List<Integer> arrayOfIntegers) {
        if (isInvalidArrayOfIntegers(arrayOfIntegers) || hasDuplicateElements(arrayOfIntegers)) {
            return 0;
        }

        return findPairs(desiredSum, arrayOfIntegers);
    }

    private boolean isInvalidArrayOfIntegers(List<Integer> arrayOfIntegers) {
        return CollectionUtils.isEmpty(arrayOfIntegers) || arrayOfIntegers.size() < 2;
    }

    private boolean hasDuplicateElements(List<Integer> arrayOfIntegers) {
        Set<Integer> uniqueElements = new HashSet<>();
        for (Integer element : arrayOfIntegers) {
            if (uniqueElements.contains(element)) {
                return true;
            }

            uniqueElements.add(element);
        }

        return false;
    }

    int findPairs(int desiredSum, List<Integer> arrayOfIntegers);
}
