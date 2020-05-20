package timer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PropertiesReaderTest {

	@Test
	public void getProperty() {

		PropertiesReader propertiesReader = new PropertiesReader();
		String value = propertiesReader.getProperties("host", "defaulValue");
		assertThat(value).isEqualTo("smtp.wp.pl");
	}

	@Test
	public void getDefaultValue() {

		PropertiesReader propertiesReader = new PropertiesReader();
		String value = propertiesReader.getProperties("noproperty", "defaulValue");
		assertThat(value).isEqualTo("defaulValue");
	}
}
