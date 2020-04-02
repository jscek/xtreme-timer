package ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    void initialize() {
        calculator = new StringCalculator();
    }

    @Test
    void addTwoNumbersTest() {
        assertEquals(4, calculator.add("1,3"));
    }

    @Test
    void addMultipleNumbersTest() {
        assertEquals(34, calculator.add("12,5,17,0"));
    }

    @Test
    void addEmptyStringTest() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void addWithNewLineTest() {
        assertEquals(12, calculator.add("3,3\n6"));
    }

    @Test
    void addWithCustomDelimiterTest() {
        assertEquals(14, calculator.add("//;\n7;4;3"));
    }

    @Test
    void addNegativeNumberTest() {
        Exception exception = assertThrows(NegativesNotAllowedException.class, () -> calculator.add("-3,3"));
        assertEquals("Negatives not allowed: -3", exception.getMessage());
    }

    @Test
    void addMultipleNegativeNumbersTest() {
        Exception exception = assertThrows(NegativesNotAllowedException.class, () -> calculator.add("-3,-8"));
        assertEquals("Negatives not allowed: -3, -8", exception.getMessage());
    }
}
