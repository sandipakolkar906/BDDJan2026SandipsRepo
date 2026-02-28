package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class DashboardPage {

	WebDriver driver;
	Scenario scenario;

	// page obecjet repo

	@FindBy(xpath = "//p[text()='Time at Work']")
	WebElement titleofTimeatWorkWidget;

	@FindBy(xpath = "//div[@class='orangehrm-attendance-card-profile-record']/child::p[1]")
	WebElement lastpunchedintimeText;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span orangehrm-attendance-card-fulltime']/child::b[1]")
	WebElement hours;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span orangehrm-attendance-card-fulltime']/child::b[2]")
	WebElement minutes;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span orangehrm-attendance-card-fulltime']")
	WebElement Day;

	@FindBy(xpath = "//p[text()='This Week']/following-sibling::p[1]")
	WebElement thisWeekSpan;

	@FindBy(xpath = "//div[@class='orangehrm-attendance-card-summary-total']/child::p[1]")
	WebElement TotalWeekHoursmins;

	// Page class constructer

	public DashboardPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}

	// page operation methods

	public HashMap getTimeatworWidgetData() {

		HashMap<String, String> objTimeatworWidgetDatamap = new HashMap<String, String>();

		objTimeatworWidgetDatamap.put("WidgetTitle", ElementActions.getText(driver, titleofTimeatWorkWidget, scenario));
		objTimeatworWidgetDatamap.put("lastpunchedinTime",
				ElementActions.getText(driver, lastpunchedintimeText, scenario));
		//ElementActions.getText(driver, hours, scenario) + " "
		//+ ElementActions.getText(driver, minutes, scenario) + " "
		
		String currentTime = ElementActions.getText(driver, Day, scenario);
		
		

		objTimeatworWidgetDatamap.put("currentTime", currentTime);
		objTimeatworWidgetDatamap.put("CurrentWeekspan", ElementActions.getText(driver, thisWeekSpan, scenario));
		objTimeatworWidgetDatamap.put("TotalWeekHoursmins",
				ElementActions.getText(driver, TotalWeekHoursmins, scenario));

		return objTimeatworWidgetDatamap;
	}

}
