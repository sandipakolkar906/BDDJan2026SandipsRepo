package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

/**
 * @author Admin
 *
 */

public class ClaimsPage {

	WebDriver driver;
	Scenario scenario;

	// Page object repo
	// Event page repo

	@FindBy(xpath = "//span[text()='Claim']")
	WebElement claimsPagelink;

	@FindBy(xpath = "//span[text()='Configuration ']")
	WebElement configMenu;

	@FindBy(xpath = "//a[text()='Events']")
	WebElement eventsOption;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addEventButton;

	@FindBy(xpath = "//label[text()='Event Name']/following::input[1]")
	WebElement eventNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveButton;

	@FindBy(xpath = "//label[text()='Event Name']/following::input[1]")
	WebElement searchByevenetNamefield;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchButton;

	@FindBy(xpath = "//div[@class='oxd-table-card']/descendant::div[5]")
	WebElement searchedEventName;

	@FindBy(xpath = "//button/child::i[@class='oxd-icon bi-pencil-fill']")
	WebElement editButton;

	@FindBy(xpath = "//button/child::i[@class='oxd-icon bi-trash']")
	WebElement deleteEventButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteConfirmButton;

	// page class constructer

	public ClaimsPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}

	// Page operation methods

	public void navigateToClaimsPage() {

		WaitMethods.staticWait(2000, scenario);

		ElementActions.clickElement(driver, claimsPagelink, scenario);

		WaitMethods.staticWait(5000, scenario);

	}

	public void navigateToEventsOption() {

		ElementActions.clickElement(driver, configMenu, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, eventsOption, scenario);
		WaitMethods.staticWait(2000, scenario);

	}

	public void addNewEvent(String eventName) {

		ElementActions.clickElement(driver, addEventButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, eventNameField, scenario, eventName);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, saveButton, scenario);

	}

	public String searchnewlyAddedEventByName(String eventName) {
		WaitMethods.staticWait(2000, scenario);
		navigateToEventsOption();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, searchByevenetNamefield, scenario, eventName);
		ElementActions.clickElement(driver, searchButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		return ElementActions.getText(driver, searchedEventName, scenario);
	}

	public void editEventName(String texttoUpdate) {
		ElementActions.clickElement(driver, editButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, eventNameField, scenario, texttoUpdate);
		ElementActions.clickElement(driver, saveButton, scenario);
		WaitMethods.staticWait(2000, scenario);

	}

	public void deletenewlyAddedEvent() {

		ElementActions.clickElement(driver, deleteEventButton, scenario);
		ElementActions.clickElement(driver, deleteConfirmButton, scenario);

	}

}
