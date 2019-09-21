package com.andre.isidoro.osm.selenium.logger;

public interface IMyLogger {

	public void info(String text);
	public void error(String text);
	public void error(String text, Exception e);
	public void warn(String text);
}
