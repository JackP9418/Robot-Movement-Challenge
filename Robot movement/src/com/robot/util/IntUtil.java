package com.robot.util;

public class IntUtil {
	public static int parse(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		}catch(NumberFormatException e) {
			return defaultValue;
		}
	}
}
