package timer.utils;

import java.time.Duration;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class TimeInputParser {

	public static Duration parseInput(String[] input) {
		Duration duration = Duration.ZERO;
		Long second = 0L;
		Long minute = 0L;
		Long hours = 0L;
		Pattern pattern1 = Pattern.compile("^([0-9]*?\\d|2[0-3])(?::([0-5]?\\d))?(?::([0-5]?\\d))?$");
		Pattern pattern2 = Pattern.compile("^(?:\\d+h)?(?:(?!\\n)\\s)?(?:\\d+m?)?(?:(?!\\n)\\s)?(?:\\d+s)?$");
		if (input.length >= 2) {
			input[0] = "";
			input[1] = "";
			String stringTime = String.join(" ", input).substring(2);

			if (pattern1.matcher(stringTime).find()) {
				String[] splitted = stringTime.split(":");
				if (splitted.length == 1) {
					second = Long.valueOf(splitted[0]);
				}
				if (splitted.length == 2) {
					minute = Long.valueOf(splitted[0]);
					second = Long.valueOf(splitted[1]);
				}
				if (splitted.length == 3) {
					hours = Long.valueOf(splitted[0]);
					minute = Long.valueOf(splitted[1]);
					second = Long.valueOf(splitted[2]);
				}
			} else if (pattern2.matcher(stringTime).find()) {
				if (stringTime.contains("h")) {
					hours = Long.valueOf(stringTime.split("h")[0]);
					if (stringTime.contains("m")) {
						minute = Long.valueOf(StringUtils.substringBetween(stringTime, "h", "m"));
						if (stringTime.contains("s")) {
							second = Long.valueOf(StringUtils.substringBetween(stringTime, "m", "s"));
						}
					}
				} else if (stringTime.contains("m")) {
					minute = Long.valueOf(stringTime.split("m")[0]);
					if (stringTime.contains("s")) {
						second = Long.valueOf(StringUtils.substringBetween(stringTime, "m", "s"));
					}
				} else if (stringTime.contains("s")) {
					second = Long.valueOf(stringTime.replace("s", ""));
				}
			}

		}
		duration = duration.plusSeconds(second);
		duration = duration.plusMinutes(minute);
		duration = duration.plusHours(hours);
		return duration;

	}
}
