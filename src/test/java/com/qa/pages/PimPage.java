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

public class PimPage {

	WebDriver driver;
	Scenario scenario;

	// page obect repo

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pimPageLink;

	@FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']/child::h6[1]")
	WebElement pimPageTitle;

	@FindBy(xpath = "//a[text()='Employee List']")
	WebElement employeeLIstLink;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addempButton;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement fNameField;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement mNameField;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveBUtton;

	// Search emp object repo

	@FindBy(xpath = "//label[text()='Employee Name']/following::input[1]")
	WebElement searchByEmpNameField;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement empSearchButton;

	@FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/child::div[3]")
	WebElement searchedFandMname;

	@FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/child::div[4]")
	WebElement searchedLname;

	// edit object repo
	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-table-cell-action-space']/child::i[@class='oxd-icon bi-pencil-fill']")
	WebElement editButton;

	// Delete object repo

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-table-cell-action-space']/child::i[@class='oxd-icon bi-trash']")
	WebElement deleteButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")

	WebElement deleteConfirmButton;

	// page class constructer

	public PimPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}
	// page operation methods

	public String navigateToPimPage() {
		ElementActions.clickElement(driver, pimPageLink, scenario);
		WaitMethods.staticWait(2000, scenario);
		return ElementActions.getText(driver, pimPageTitle, scenario);

	}

	// delete emp

	public void deleteEmp() {
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, employeeLIstLink, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, deleteButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, deleteConfirmButton, scenario);

	}

	// Edit emp

	public void editEmp(String fNametoappend, String mNametoappend, String lNametoappend) {

		ElementActions.clickElement(driver, editButton, scenario);
		WaitMethods.staticWait(5000, scenario);
		ElementActions.sendKeys(driver, fNameField, scenario, fNametoappend);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, mNameField, scenario, mNametoappend);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, lNameField, scenario, lNametoappend);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, saveBUtton, scenario);
		WaitMethods.staticWait(2000, scenario);

	}

	// SearchEmp

	public HashMap<String, String> searhEmpbyName(String fName) {
		ElementActions.clickElement(driver, employeeLIstLink, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, searchByEmpNameField, scenario, fName);

		Actions objactions = new Actions(driver);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000, scenario);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, empSearchButton, scenario);
		WaitMethods.staticWait(2000, scenario);

		HashMap<String, String> empNameMap = new HashMap<String, String>();

		empNameMap.put("fNammeandmName", ElementActions.getText(driver, searchedFandMname, scenario));

		empNameMap.put("lName", ElementActions.getText(driver, searchedLname, scenario));

		return empNameMap;

	}

	// Addemp

	public void addEmployee(String fName, String mName, String lName) {
		ElementActions.clickElement(driver, employeeLIstLink, scenario);

		ElementActions.clickElement(driver, addempButton, scenario);

		ElementActions.sendKeys(driver, fNameField, scenario, fName);

		ElementActions.sendKeys(driver, mNameField, scenario, mName);

		ElementActions.sendKeys(driver, lNameField, scenario, lName);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, saveBUtton, scenario);
		WaitMethods.staticWait(5000, scenario);
	}

}
