package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {
        final int[] ARRAY_OF_NUMBERS = {1, 6, 8, 1, 5, 3, 3, 6, 5};
        final List<Integer> listOfNumbers =
                Arrays.stream(ARRAY_OF_NUMBERS).boxed().distinct().collect(Collectors.toList());

        System.out.println("First task: " + minValue(ARRAY_OF_NUMBERS));
        System.out.println("Second task:");
        oddOrEven(listOfNumbers).forEach(System.out::print);
    }

    private static int minValue(int[] arrayOfNumbers) {
        return Arrays.stream(arrayOfNumbers).distinct().sorted()
                .reduce(0, (partialResult, currentNumber) -> partialResult * 10 + currentNumber);
    }

    private static List<Integer> oddOrEven(List<Integer> listOfNumbers) {
        int sum = listOfNumbers.stream().reduce(0, Integer::sum);

        return listOfNumbers.stream().filter(i -> i % 2 != sum % 2).collect(Collectors.toList());
    }
}
