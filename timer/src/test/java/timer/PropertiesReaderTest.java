package timer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import timer.utils.PropertiesReader;

public class PropertiesReaderTest {

	@Test
	public void getProperty() {

		PropertiesReader propertiesReader = PropertiesReader.getInstance();
		String value = propertiesReader.getProperty("host", "defaulValue");
		assertThat(value).isEqualTo("smtp.wp.pl");
	}

	@Test
	public void getDefaultValue() {

		PropertiesReader propertiesReader = PropertiesReader.getInstance();
		String value = propertiesReader.getProperty("noproperty", "defaulValue");
		assertThat(value).isEqualTo("defaulValue");
	}
}
