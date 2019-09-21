package com.andre.isidoro.osm.selenium.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ContextChrome implements Context {
	private WebDriver driver;

	ContextChrome(WebDriver driver) {
		this.driver = driver;
	}


	public ContextChrome(String pathToDriver) {
		// System.setProperty("webdriver.chrome.driver","F:\\programs\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", pathToDriver);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary("/usr/bin/google-chrome");
		//chromeOptions.addArguments("headless");
		chromeOptions.addArguments("disable-gpu");
		chromeOptions.addArguments("disable-dev-shm-usage");
		chromeOptions.addArguments("no-sandbox");
		chromeOptions.addArguments("no-gpu");
		chromeOptions.addArguments("mute-audio");
		chromeOptions.addArguments("disable-software-rasterizer");
		chromeOptions.addArguments("hide-scrollbars");
		this.driver = new ChromeDriver(chromeOptions);
	}

	public void init() {

	}

	public WebDriver getWebDriver() {
		return this.driver;
	}
}
