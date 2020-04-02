
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
        return (int) binaryString
                .chars()
                .filter(c -> c == (int) '1')
                .count();
    }


    private String getBinaryRepresentationString(String[] numbers) {
        StringBuilder binaryString = new StringBuilder();
        for (String number : numbers) {
            binaryString.append(getBinaryRepresentationString(number));
        }
        return binaryString.toString();
    }

    private String getBinaryRepresentationString(String number) {
        int value = Integer.parseInt(number);
        if (value < 0 || value >= 255) {
            throw new RuntimeException("Parameter value should be lower higher than 0 and smaller than 255");
        }
        return Integer.toBinaryString(value);
    }

    private boolean hasMultipleNumbers(String numbers) {
        return numbers.matches(".*" + delimitersRegex +".*");
    }
}
