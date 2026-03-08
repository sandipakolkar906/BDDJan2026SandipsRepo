package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	// Page object repo for claims

	@FindBy(xpath = "//a[text()='Submit Claim']")
	WebElement submitclaimsPageLink;

	@FindBy(xpath = "//label[text()='Event']/following::i[1]")
	WebElement eventDropdownButton;

	@FindBy(xpath = "//label[text()='Currency']/following::i[1]")
	WebElement currencyDropdownButton;

	@FindBy(xpath = "//button[text()=' Create ']")
	WebElement createButton;

	@FindBy(xpath = "//h6[text()='Expenses']/following::button[1]")
	WebElement addExpenseButton;

	@FindBy(xpath = "//label[text()='Expense Type']/following::i[1]")
	WebElement expenseTypeDropdownButton;

	@FindBy(xpath = "//label[text()='Date']/following::input[1]")
	WebElement expendeDatefield;

	@FindBy(xpath = "//label[text()='Amount']/following::input[1]")
	WebElement expendeamountfield;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveExpenseButton;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p']")
	WebElement totalamounttext;

	// page class constructer

	public ClaimsPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}

	
	// claims page operations
	
	
	public void navigateToSubmitClaimsPage(){
		
		ElementActions.clickElement(driver, submitclaimsPageLink, scenario);
		WaitMethods.staticWait(2000, scenario);
		
	}
	
	
	public void createClaim(){
		
		
		Actions objactions= new Actions(driver);
		
		ElementActions.clickElement(driver, eventDropdownButton, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, currencyDropdownButton, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, createButton, scenario);
		WaitMethods.staticWait(5000, scenario);
		
		
	}
	
	
	public void addExpense(String date, String amount){
		
		ElementActions.clickElement(driver, addExpenseButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		Actions objactions= new Actions(driver);
		ElementActions.clickElement(driver, expenseTypeDropdownButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, expendeDatefield, scenario, date);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver,expendeamountfield , scenario, amount);
		ElementActions.clickElement(driver, saveExpenseButton, scenario);
		WaitMethods.staticWait(5000, scenario);
	}
	
	
	public double  getTotalAmount(){
		
		return Double.parseDouble(ElementActions.getText(driver, totalamounttext, scenario).split(":")[1].trim());
		
	}
	
	// Event Page operation methods

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
