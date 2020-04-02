import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.NumberFormatException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BitCounterTest {

    private BitCounter bitCounter;

    @BeforeEach
    void beforeEach() {
        bitCounter = new BitCounter();
    }

    @Test
    void noOfBits1Test() {
        assertThat(bitCounter.noOfBits1("4"))
                .isEqualTo(1);

        assertThat(bitCounter.noOfBits1("123"))
                .isEqualTo(6);
    }

    @Test
    void noOfBits1EmptyStringTest() {
        assertThat(bitCounter.noOfBits1(""))
                .isEqualTo(0);
    }

    @Test
    void noOfBits1IncorrectParameterTest() {
        assertThrows(RuntimeException.class, () -> bitCounter.noOfBits1("123123"));
        assertThrows(RuntimeException.class, () -> bitCounter.noOfBits1("-5"));
    }

    @Test
    void noOfBits1MultipleNumbersTest() {
        assertThat(bitCounter.noOfBits1("3;5"))
                .isEqualTo(4);
    }

    @Test
    void noOfBits1SpaceAndSemicolonDelimitersTest() {
        assertThat(bitCounter.noOfBits1("3;5  8"))
                .isEqualTo(5);
    }

    @Test
    void noOfBits1WhiteSpacesDelimitersTest() {
        assertThat(bitCounter.noOfBits1("3 5     8"))
                .isEqualTo(5);

        assertThat(bitCounter.noOfBits1("3;5\n8"))
                .isEqualTo(5);
    }

    @Test
    void noOfBits1InvalidSeparatorTest() {
        assertThrows(NumberFormatException.class, () -> bitCounter.noOfBits1("3:3:2:4"));
    }

}