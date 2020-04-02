import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BitGeneratorTest {
    
    private BitGenerator generator;
    
    @BeforeEach
    void beforeEach() {
        generator = new BitGenerator();
    }
    
    @Test
    void noOfBits1Test() {
        assertThat(generator.noOfBits1("4"))
                .isEqualTo(1);

        assertThat(generator.noOfBits1("123"))
                .isEqualTo(6);
    }

}