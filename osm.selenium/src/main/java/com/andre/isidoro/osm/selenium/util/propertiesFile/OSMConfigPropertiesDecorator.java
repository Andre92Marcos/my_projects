package com.andre.isidoro.osm.selenium.util.propertiesFile;

public class OSMConfigPropertiesDecorator implements IOPropertiesFiles{
	
	private static final String PATH_TO_CONFIG_FILE = "/tmp/osm.selenium/osmSeleniumConfig.properties";
	private static final String KEY_PREFIX = "osm.selenium.";
	private IOPropertiesFiles ioPropertiesFiles;
	
	public OSMConfigPropertiesDecorator() {
		ioPropertiesFiles = new IOPropertiesFilesBasic(PATH_TO_CONFIG_FILE);
	}
	

	@Override
	public String getProperty(String key) {
		return ioPropertiesFiles.getProperty(KEY_PREFIX + key);
	}


}
