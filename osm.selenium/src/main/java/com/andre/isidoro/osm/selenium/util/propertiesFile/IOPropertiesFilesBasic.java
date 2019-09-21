package com.andre.isidoro.osm.selenium.util.propertiesFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.andre.isidoro.osm.selenium.App;
import com.andre.isidoro.osm.selenium.logger.IMyLogger;
import com.andre.isidoro.osm.selenium.logger.MyLoggerFactory;

public class IOPropertiesFilesBasic implements IOPropertiesFiles {

	private static final IMyLogger LOGGER = MyLoggerFactory.getMyLogger(App.class);

	private InputStream input = null;
	private Properties prop = null;

	public IOPropertiesFilesBasic(String pathToPropertiesFile) {
		try {
			this.input = new FileInputStream(pathToPropertiesFile);
			this.prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			LOGGER.error("Could not open file " + pathToPropertiesFile);
		}
	}

	@Override
	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
