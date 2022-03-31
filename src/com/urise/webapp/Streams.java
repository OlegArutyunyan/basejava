package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    private static final int[] ARRAY_OF_NUMBERS = {1, 6, 8, 1, 5, 3, 3, 6, 5};
    private static final List<Integer> listOfNumbers =
            Arrays.stream(ARRAY_OF_NUMBERS).boxed().distinct().collect(Collectors.toList());

    public static void main(String[] args) {
        System.out.println("First task: " + minValue());
        System.out.println("Second task:");
        oddOrEven().forEach(System.out::print);
    }

    private static int minValue() {
        return Arrays.stream(Streams.ARRAY_OF_NUMBERS).distinct().sorted()
                .reduce(0, (partialResult, currentNumber) -> partialResult * 10 + currentNumber);
    }

    private static List<Integer> oddOrEven() {
        int sum = Streams.listOfNumbers.stream().reduce(0, Integer::sum);

        return Streams.listOfNumbers.stream().filter(i -> i % 2 != sum % 2).collect(Collectors.toList());
    }
}
