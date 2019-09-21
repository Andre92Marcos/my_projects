package com.andre.isidoro.osm.selenium.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.andre.isidoro.osm.selenium.context.ContextManager;

public class SeleniumUtils {

	public static WebElement fluentWait(final By locator, WebDriver driver) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(15, TimeUnit.SECONDS)
	            .pollingEvery(1, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};
	
	public static void findButtonAndClick(String xpathToButton) {
		SeleniumUtils.fluentWait(By.xpath(xpathToButton) , ContextManager.getContextManager().getCurrentContext().getWebDriver());
		WebElement button = ContextManager.getContextManager().getCurrentContext().getWebDriver().findElement(By.xpath(xpathToButton));		
		ContextManager.getContextManager().getCurrentContext().getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		button.click();
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
	}
	
	public static void findButtonAndClickByIndex(String xpathToButton, int index) {
		SeleniumUtils.fluentWait(By.xpath(xpathToButton) , ContextManager.getContextManager().getCurrentContext().getWebDriver());
		List<WebElement> buttons = ContextManager.getContextManager().getCurrentContext().getWebDriver().findElements(By.xpath(xpathToButton));		
		ContextManager.getContextManager().getCurrentContext().getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		buttons.get(index).click();
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
	}
	
	public static void findInputAndWriteAndReturn(String xpathToInput, String keys) {
		SeleniumUtils.fluentWait(By.xpath(xpathToInput) , ContextManager.getContextManager().getCurrentContext().getWebDriver());
		WebElement input = ContextManager.getContextManager().getCurrentContext().getWebDriver().findElement(By.xpath(xpathToInput));		
		ContextManager.getContextManager().getCurrentContext().getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		input.sendKeys(keys);
		input.sendKeys(Keys.RETURN);
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
	}
	
	public static void findInputAndWrite(String xpathToInput, String keys) {
		SeleniumUtils.fluentWait(By.xpath(xpathToInput) , ContextManager.getContextManager().getCurrentContext().getWebDriver());
		WebElement input = ContextManager.getContextManager().getCurrentContext().getWebDriver().findElement(By.xpath(xpathToInput));		
		ContextManager.getContextManager().getCurrentContext().getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		input.sendKeys(keys);
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
	}
	
	public static WebElement getElement(String xpath) {
		SeleniumUtils.fluentWait(By.xpath(xpath) , ContextManager.getContextManager().getCurrentContext().getWebDriver());
		WebElement element = ContextManager.getContextManager().getCurrentContext().getWebDriver().findElement(By.xpath(xpath));		
		ContextManager.getContextManager().getCurrentContext().getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return element;
	}
	
	public static WebElement getElementByIndex(String xpath, int index) {
		SeleniumUtils.fluentWait(By.xpath(xpath) , ContextManager.getContextManager().getCurrentContext().getWebDriver());
		List<WebElement> elements = ContextManager.getContextManager().getCurrentContext().getWebDriver().findElements(By.xpath(xpath));		
		ContextManager.getContextManager().getCurrentContext().getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return elements.get(index);
	}
	
	public static boolean doesElementExist(String xpath) {
		try {
			ContextManager.getContextManager().getCurrentContext().getWebDriver().findElement(By.xpath(xpath));
		}catch(NoSuchElementException e) {
			return false;
		}
		return true;
	}
}
