package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;

import cucumber.api.Scenario;

/**
 * @author Admin
 * 
 *  this is log in page class which has page repo and page operation methods
 *  
 */

public class LoginPage {
	
	
	WebDriver driver;
	Scenario scenario;
	
	// Page object Repository 
	// add elements or locators using @findby annotation
	
	
	@FindBy(xpath="//input[@name='username']")
	WebElement userNameField;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordField;
	
	
	@FindBy(xpath="//button[text()=' Login ']")
	WebElement loginButton;
	
	
	
	// Page class constructer 
	
	public LoginPage(WebDriver driver, Scenario scenario){
		
		this.driver=driver;
		this.scenario=scenario;
		PageFactory.initElements(driver, this);
		
	}
	
	
	// Page operation Methods
	
	public void login(String userName, String password){
		
		ElementActions.sendKeys(driver, userNameField, scenario, userName);
		ElementActions.sendKeys(driver, passwordField, scenario, password);
		ElementActions.clickElement(driver, loginButton, scenario);
		
	}
	
	
	
	

}
