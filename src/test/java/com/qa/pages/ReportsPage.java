package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class ReportsPage {

	WebDriver driver;
	Scenario scenario;

	// Page object repo

	@FindBy(xpath = "//a[text()='Reports']")

	WebElement ReportsPageLink;

	@FindBy(xpath = "//button[text()=' Add ']")

	WebElement addReportsButton;

	@FindBy(xpath = "//label[text()='Report Name']/following::input[1]")

	WebElement reportNamefield;

	@FindBy(xpath = "//label[text()='Select Display Field Group']/following::i[1]")

	WebElement displayFieldGropDropdown;

	@FindBy(xpath = "//label[text()='Select Display Field']/following::i[1]")

	WebElement displayFieldDropdown;

	@FindBy(xpath = "//label[text()='Select Display Field']/following::button[1]")

	WebElement addDisplayFieldButton;

	@FindBy(xpath = "//button[text()=' Save ']")

	WebElement saveButton;

	@FindBy(xpath = "//button[text()=' Search ']")

	WebElement searchReportButton;

	@FindBy(xpath = "//div[@class='oxd-table-card']/child::div[1]/child::div[2]")

	WebElement searchedReportName;

	@FindBy(xpath = "//button/child::i[@class='oxd-icon bi-file-text-fill']")

	WebElement generateReportButton;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/child::div[1]")

	WebElement firstReportField;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/child::div[2]")

	WebElement secondField;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/child::div[3]")

	WebElement thirdField;

	// page class constructer
	public ReportsPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}

	// Page operation methods

	public void navigateToReportsPage() {

		ElementActions.clickElement(driver, ReportsPageLink, scenario);
		WaitMethods.staticWait(5000, scenario);
	}

	public void addNewReport(String reportName) {

		ElementActions.clickElement(driver, addReportsButton, scenario);
		WaitMethods.staticWait(5000, scenario);
		ElementActions.sendKeys(driver, reportNamefield, scenario, reportName);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, displayFieldGropDropdown, scenario);
		WaitMethods.staticWait(2000, scenario);

		Actions objactions = new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000, scenario);

		for (int i = 0; i <= 2; i++) {
			ElementActions.clickElement(driver, displayFieldDropdown, scenario);
			WaitMethods.staticWait(2000, scenario);
			objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
			WaitMethods.staticWait(2000, scenario);
			objactions.sendKeys(Keys.ENTER).build().perform();
			WaitMethods.staticWait(2000, scenario);
			ElementActions.clickElement(driver, addDisplayFieldButton, scenario);
			WaitMethods.staticWait(2000, scenario);
		}

		ElementActions.clickElement(driver, saveButton, scenario);
		WaitMethods.staticWait(5000, scenario);

	}

	public String searchReport(String reportName) {

		navigateToReportsPage();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, reportNamefield, scenario, reportName);
		WaitMethods.staticWait(2000, scenario);
		Actions objactions= new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, searchReportButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		return ElementActions.getText(driver, searchedReportName, scenario);
	}

	public HashMap<String, String> getReportFields() {

		ElementActions.clickElement(driver, generateReportButton, scenario);
		WaitMethods.staticWait(5000, scenario);

		HashMap<String, String> generatedReportFields = new HashMap<String, String>();

		generatedReportFields.put("firstField", ElementActions.getText(driver, firstReportField, scenario));
		generatedReportFields.put("SecondField", ElementActions.getText(driver, secondField, scenario));
		generatedReportFields.put("thirdField", ElementActions.getText(driver, thirdField, scenario));
		return generatedReportFields;

	}

}
