package com.qa.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.Scenario;
// To add comment for method use keys like alt+shift+j
/**
 * @author Punam
 * This class has methods to click, sendkeys etc with configured dynamic wait time and exception handling
 */
public class ElementActions {

	/**
	 * @param driver
	 * @param elem
	 * @param scenario
	 * This method will clear the existing values from the field
	 */
	public static void clearFieldvalue(WebDriver driver, WebElement elem, Scenario scenario) {

		try {
			WaitMethods.waitFor(driver, elem, WaitMethods.ELEMENT_TO_BE_CLICKABLE, scenario);
		} catch (Exception E) {
			scenario.write(" Error while waiting for clicking on Element ");
		}
		try {
			elem.clear();
		} catch (Exception E) {
			scenario.write(" Error on clicking the element after wating !");
		}

	}
	/**
	 * @param driver
	 * @param elem
	 * @param scenario
	 * This method will take element as parameter and wait for 
	 * explicit wait time and then perform click operation
	 */
	public static void clickElement(WebDriver driver, WebElement elem, Scenario scenario) {

		try {
			WaitMethods.waitFor(driver, elem, WaitMethods.ELEMENT_TO_BE_CLICKABLE, scenario);
		} catch (Exception E) {
			scenario.write(" Error while waiting for clicking on Emmenet ");
		}
		try {
			elem.click();
		} catch (Exception E) {
			scenario.write(" Error on clicking the element after wating !");
		}

	}
	/**
	 * @param driver
	 * @param elem
	 * @param scenario
	 * @param texttoType
	 * This method will accept the text to type and wait for fluent
	 * wait time until element is clickable and then type text
	 */
	public static void sendKeys(WebDriver driver, WebElement elem, Scenario scenario, String texttoType) {

		try {
			WaitMethods.waitFor(driver, elem, WaitMethods.ELEMENT_TO_BE_CLICKABLE, scenario);
		} catch (Exception E) {
			scenario.write(" Error while waiting for clicking on Emmenet ");
		}

		try {
			elem.sendKeys(texttoType);
		} catch (Exception E) {
			scenario.write(" Error on typeing the text in element after wating !");
		}

	}
	
	/**
	 * @param driver
	 * @param elem
	 * @param scenario
	 * @return 
	 * This method will wait for fluent wait time and when element is 
	 * visible get the text and return the string value
	 */
	public static String getText(WebDriver driver, WebElement elem, Scenario scenario) {

		String texttoReturn = null;
		try {
			WaitMethods.waitFor(driver, elem, WaitMethods.ELEMENT_TO_BE_VISIBLE, scenario);
		} catch (Exception E) {
			scenario.write(" Error while waiting for Element visibility  ");
		}
		try {
			texttoReturn = elem.getText();
		} catch (Exception E) {
			scenario.write(" Error on fetching  the text in element after wating !");
		}
		return texttoReturn;

	}
	
	/**
	 * @param driver
	 * @param elem
	 * @param scenario
	 * @param option
	 * This will accept option as parameter and select the 
	 * value from the dropdown
	 */
	public static void selectOptionFromDropDown(WebDriver driver, WebElement elem, Scenario scenario, String option) {

		try {
			WaitMethods.waitFor(driver, elem, WaitMethods.ELEMENT_TO_BE_CLICKABLE, scenario);
		} catch (Exception E) {
			scenario.write(" Error while waiting for Element to be clickable   ");
		}

		Select objselect = new Select(elem);

		try {
			objselect.selectByValue(option);
		} catch (Exception E) {
			scenario.write(" Error while selecting the option from Dropdown! ");

		}

	}

}
