package com.andre.isidoro.osm.selenium.util.propertiesFile;

public class IOPropertiesFilesFactory {
	
	private static IOPropertiesFiles twgConfig = new OSMConfigPropertiesDecorator();
	
	public static IOPropertiesFiles getIOPropertiesFiles() {
		return twgConfig;
	}
}
