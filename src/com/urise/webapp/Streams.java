package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    private static final int[] ARRAY_OF_NUMBERS = {1, 6, 8, 1, 5, 3, 3, 6, 5};

    public static void main(String[] args) {
        System.out.println("First task: " + minValue(ARRAY_OF_NUMBERS));
    }

    private static int minValue(int[] values) {
        int result = 0;
        List<Integer> listOfNumbers = Arrays.stream(values).boxed().distinct().sorted().collect(Collectors.toList());
        System.out.println("Second task:");
        oddOrEven(listOfNumbers).forEach(System.out::print);
        System.out.println();

        for (Integer number : listOfNumbers) {
            result = result * 10 + number;
        }
        return result;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce(0, Integer::sum) % 2 == 0) {
            return integers.stream().filter(i -> i % 2 == 1).collect(Collectors.toList());
        }
        return integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    }
}
