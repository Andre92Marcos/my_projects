package com.andre.isidoro.osm.selenium.context;

public class ContextManager {

	private static Context currentContext = null;
	private static ContextManager instance = null;
	
	private ContextManager() {
		currentContext = new ContextChrome(ContextType.CHROME_LINUX.toString());
	}
	
	public static ContextManager getContextManager() {
		if(instance == null) {
			instance = new ContextManager();
		}
		return instance;
	}

	public Context startNewContext() {
		currentContext = new ContextChrome(ContextType.CHROME_LINUX.toString());
		return currentContext;
	}
	
	public Context getCurrentContext() {
		return currentContext;
	}
}
