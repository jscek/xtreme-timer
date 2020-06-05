package timer.utils;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PropertiesReader {

	private JSONObject jsonObject = new JSONObject();
	JSONParser parser = new JSONParser();

	public PropertiesReader() {
		try {
			Object obj = parser.parse(new FileReader("properties.json"));
			jsonObject = (JSONObject) obj;
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		if (jsonObject == null) {
			jsonObject = new JSONObject();
		}
	}

	public String getProperties(String propertyName, String defaultValue) {
		String value = (String) jsonObject.get(propertyName);
		return value != null ? value : defaultValue;
	}
}
