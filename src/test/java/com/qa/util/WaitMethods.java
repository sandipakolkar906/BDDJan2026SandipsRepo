package com.qa.util;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import cucumber.api.Scenario;

/**
 * @author Punam
 * This class has common methods to configure the implicit and explicit wait
 */
public class WaitMethods {

	WebDriver driver;
	
	public static String ELEMENT_TO_BE_CLICKABLE = "elementTobeClickable";
	public static String ELEMENT_TO_BE_VISIBLE = "visibilityOf";
	public static String STALENESS_OF_ELEMENT = "stalenessof";
	
	/**
	 * @param driver
	 * @param elem
	 * @param waitType
	 * @param scenario
	 * @return
	 * 
	 * Dynamic wait : 
	 */
	public static WebElement waitFor(WebDriver driver, WebElement elem, String waitType, Scenario scenario) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(ReadProperties.getFluentWaitTime(), TimeUnit.SECONDS)
				.pollingEvery(60, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		try {
			switch (waitType) {
			case "elementTobeClickable":
				wait.until(ExpectedConditions.elementToBeClickable(elem));
				break;
			case "visibilityOf":
				wait.until(ExpectedConditions.visibilityOf(elem));
				break;
			case "elementToBeSelected":
				wait.until(ExpectedConditions.elementToBeSelected(elem));
				break;
			case "stalenessof":
				wait.until(ExpectedConditions.stalenessOf(elem));
				break;
			default:
				wait.until(ExpectedConditions.visibilityOf(elem));
			}

		} catch (Exception E) {

			scenario.write("There is some Exception in Fluent Wait checking--  " + E.getMessage());
		}

		return elem;
	}
	
	/**
	 * @param milisec
	 * This is a static wait written with exception handling
	 */
	public static void staticWait(long milisec, Scenario scenario) {

		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			scenario.write("There is some Exception in Static Wait or therad.sleep--  " + e.getMessage());
		}
	}
	
}
