package com.andre.isidoro.osm.selenium.context;

import com.andre.isidoro.osm.selenium.util.propertiesFile.IOPropertiesFilesFactory;

public enum ContextType {

	CHROME_LINUX(IOPropertiesFilesFactory.getIOPropertiesFiles().getProperty("CHROME_DRIVER_PATH")), CHROME_DOCKER(IOPropertiesFilesFactory.getIOPropertiesFiles().getProperty("CHROME_DRIVER_URL"));
	
	private String type;
	
	private ContextType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
