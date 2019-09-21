package com.andre.isidoro.osm.selenium.context;

import org.openqa.selenium.WebDriver;

public interface Context {
	public void init();
	
	public WebDriver getWebDriver();
}
