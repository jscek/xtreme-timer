package ex2;

public class BitCounter {
    private final String delimitersRegex = "(\\s+|;)";

    public int noOfBits1(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String binaryString;
        if (hasMultipleNumbers(numbers)) {
            binaryString = getBinaryRepresentationString(numbers.split(delimitersRegex));
        } else {
            binaryString = getBinaryRepresentationString(numbers);
        }
        return getCountOfBits1(binaryString);
    }

    private String getBinaryRepresentationString(String[] numbers) {
        StringBuilder binaryString = new StringBuilder();
        for (String number : numbers) {
            binaryString.append(getBinaryRepresentationString(number));
        }
        return binaryString.toString();
    }

    private String getBinaryRepresentationString(String number) {
        int value = parseStringToInt(number);
        if (value < 0 || value >= 255) {
            throw new RuntimeException("Parameter value should be lower higher than 0 and smaller than 255");
        }
        return Integer.toBinaryString(value);
    }

    private int parseStringToInt(String number) {
        if (number.startsWith("$")) {
            return Integer.parseInt(number.substring(1), 16);
        }
        return Integer.parseInt(number);
    }

    private boolean hasMultipleNumbers(String numbers) {
        return numbers.matches(".*" + delimitersRegex +".*");
    }

    private int getCountOfBits1(String binaryString) {
        return (int) binaryString
                .chars()
                .filter(c -> c == (int) '1')
                .count();
    }
}
