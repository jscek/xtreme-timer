
public class BitCounter {

    public int noOfBits1(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        String binaryString = getBinaryRepresentationString(s);

        return (int) binaryString
                .chars()
                .filter(c -> c == (int) '1')
                .count();
    }

    private String getBinaryRepresentationString(String number) {
        int value = Integer.parseInt(number);
        if (value < 0 || value >= 255) {
            throw new RuntimeException("Parameter value should be lower higher than 0 and smaller than 255");
        }
        return Integer.toBinaryString(value);
    }
}
