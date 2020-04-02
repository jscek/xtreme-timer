
public class BitGenerator {

    public int noOfBits1(String s) {
        int number = Integer.parseInt(s);
        return (int) Integer.toBinaryString(number)
                .chars()
                .filter(c -> c == (int) '1')
                .count();
    }
}
