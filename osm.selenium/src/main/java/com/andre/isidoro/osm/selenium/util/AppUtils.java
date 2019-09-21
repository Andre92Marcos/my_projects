package com.andre.isidoro.osm.selenium.util;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.andre.isidoro.osm.selenium.context.ContextManager;
import com.andre.isidoro.osm.selenium.logger.IMyLogger;
import com.andre.isidoro.osm.selenium.logger.MyLoggerFactory;

public class AppUtils {
	
	private static final IMyLogger LOGGER = MyLoggerFactory.getMyLogger(AppUtils.class);
	
	public static final long PAGE_LOAD_TIMEOUT = 5 * 1000;
	public static final long RECOVERY_TIMEOUT = 5 * 60 * 1000;
	public static final long RELOAD_TIMEOUT = 60 * 60; // min * secs
	public static final long WAIT_FOR_LOGIN_TIME = 7 * 1000;
	
	public static void waitBeforeNextAction() {
		try {
			int seconds = randomInRange(2, 4);
			LOGGER.info("Sleeping for " + seconds + " seconds");
			Thread.sleep(seconds * randomMillis());
		} catch (InterruptedException e) {
		}
	}

	
	public static boolean waitForPageToFullyLoad() {
		new WebDriverWait(ContextManager.getContextManager().getCurrentContext().getWebDriver(), PAGE_LOAD_TIMEOUT).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		return true;
	}
	
	public static boolean waitForPageToFullyLoadAndBeforeNextAction() {
		waitForPageToFullyLoad();
		waitBeforeNextAction();
		return true;
	}
	
	public static int randomInRange(int lower, int higher) {
		Random r = new Random();
		return r.nextInt(higher-lower) + lower;
	}

	public static int randomMillis() {
		return randomInRange(850,1001);
	}
}
