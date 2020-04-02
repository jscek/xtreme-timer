import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BitCounterTest {

    private BitCounter generator;

    @BeforeEach
    void beforeEach() {
        generator = new BitCounter();
    }

    @Test
    void noOfBits1Test() {
        assertThat(generator.noOfBits1("4"))
                .isEqualTo(1);

        assertThat(generator.noOfBits1("123"))
                .isEqualTo(6);
    }

    @Test
    void noOfBits1EmptyStringTest() {
        assertThat(generator.noOfBits1(""))
                .isEqualTo(0);
    }

    @Test
    void noOfBits1IncorrectParameterTest() {
        assertThrows(RuntimeException.class, () -> generator.noOfBits1("123123"));
        assertThrows(RuntimeException.class, () -> generator.noOfBits1("-5"));
    }

    @Test
    void noOfBits1MultipleNumbersTest() {
        assertThat(generator.noOfBits1("3;5"))
                .isEqualTo(4);
    }
}