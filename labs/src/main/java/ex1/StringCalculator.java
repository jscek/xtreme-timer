package ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String stringOfNumbers) {
        if (stringOfNumbers.isEmpty()) {
            return 0;
        }
        int[] numbersArray = extractNumbersFromString(stringOfNumbers);
        return sumNumbersFromArray(numbersArray);
    }

    private int[] extractNumbersFromString(String stringOfNumbers) {
        String[] numbersArray = splitNumbersIntoArray(stringOfNumbers);
        int[] integerArray = parseArrayOfStringToInteger(numbersArray);
        checkIfAllNumbersAreGreaterOrEqualToZero(integerArray);
        return integerArray;
    }

    private int[] parseArrayOfStringToInteger(String[] stringArray) {
        int[] integerArray = new int[stringArray.length];

        for (int i = 0; i < integerArray.length; i++) {
            integerArray[i] = Integer.parseInt(stringArray[i]);
        }
        return integerArray;
    }

    private void checkIfAllNumbersAreGreaterOrEqualToZero(int[] numbers) {
        List<Integer> negativeNumbers = new ArrayList<>();
        for (int number : numbers) {
            if (number < 0) {
                negativeNumbers.add(number);
            }
        }
        if (!negativeNumbers.isEmpty()) {
            throw new NegativesNotAllowedException("Negatives not allowed: " + negativeNumbers
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
        }
    }

    private int sumNumbersFromArray(int[] numbersArray) {
        int result = 0;
        for (int number : numbersArray) {
            result += number;
        }
        return result;
    }

    private String[] splitNumbersIntoArray(String numbersString) {
        String delimiter = "([,\\n])";
        if (numbersString.matches("^//.*\n.*")) {
            Scanner sc = new Scanner(numbersString);
            delimiter = sc.nextLine().substring(2);
            numbersString = sc.nextLine();
        }
        return numbersString.split(delimiter);
    }

}
