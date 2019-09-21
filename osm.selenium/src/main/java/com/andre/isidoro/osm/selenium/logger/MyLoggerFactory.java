package com.andre.isidoro.osm.selenium.logger;

public class MyLoggerFactory {

	
	public static IMyLogger getMyLogger(Class<?> c) {
		return new MyLogger(c);
	}
}
