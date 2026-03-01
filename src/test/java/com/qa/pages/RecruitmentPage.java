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

public class RecruitmentPage {

	WebDriver driver;
	Scenario scenario;

	// page object repo

	@FindBy(xpath = "//span[text()='Recruitment']")
	WebElement recruitmentPageLink;

	@FindBy(xpath = "//h6[text()='Recruitment']")
	WebElement recruitMentPageTitle;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addCandidateButton;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement middleName;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastName;

	@FindBy(xpath = "//label[text()='Email']/following::input[1]")
	WebElement email;

	@FindBy(xpath = "	//button[text()=' Save ']")
	WebElement saevButton;

	@FindBy(xpath = "//a[text()='Candidates']")
	WebElement candidatesTab;

	@FindBy(xpath = "//div[@class='oxd-table-card']/descendant::div[8]")
	WebElement searchedCandidateName;

	@FindBy(xpath = "//div[@class='oxd-table-card']/descendant::div[12]")
	WebElement dateOfApplciation;

	@FindBy(xpath = "//button/child::i[@class='oxd-icon bi-trash']")
	WebElement deleteButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement confirmDeleteButton;

	// page class constructer

	public RecruitmentPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}

	// page operation methods

	public String navigateToRecruitmentPage() {

		ElementActions.clickElement(driver, recruitmentPageLink, scenario);

		WaitMethods.staticWait(2000, scenario);
		return ElementActions.getText(driver, recruitMentPageTitle, scenario);

	}

	public void addNewCandidate(String fName, String mName, String lName, String mail) {

		ElementActions.clickElement(driver, addCandidateButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, firstName, scenario, fName);
		ElementActions.sendKeys(driver, middleName, scenario, mName);
		ElementActions.sendKeys(driver, lastName, scenario, lName);
		ElementActions.sendKeys(driver, email, scenario, mail);
		ElementActions.clickElement(driver, saevButton, scenario);
		WaitMethods.staticWait(5000, scenario);
	}

	public void navigateToCandidateListTab() {

		ElementActions.clickElement(driver, candidatesTab, scenario);
		WaitMethods.staticWait(2000, scenario);

	}

	public String getNewlyAddedCandiateName() {
		return ElementActions.getText(driver, searchedCandidateName, scenario);
	}

	public String getNEwlyAddedCanddiateDate() {
		return ElementActions.getText(driver, dateOfApplciation, scenario);
	}

	public void deleteNewlyAddedCandidate() {

		ElementActions.clickElement(driver, deleteButton, scenario);
		ElementActions.clickElement(driver, confirmDeleteButton, scenario);
	}

}
