package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class BuzzPage {

	WebDriver driver;
	Scenario scenario;

	// page object repo

	@FindBy(xpath = "//span[text()='Buzz']")
	WebElement buzzPageLink;

	@FindBy(xpath = "//textarea[@class='oxd-buzz-post-input']")
	WebElement commentField;

	@FindBy(xpath = "//button[text()=' Post ']")
	WebElement postButton;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-emp-name']")
	WebElement posterName;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-time']")
	WebElement postedTime;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-body-text']")
	WebElement postedCommentText;

	@FindBy(xpath = "//div[@class='orangehrm-buzz-post-actions']/child::div[1]")
	WebElement likeButton;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-buzz-stats-active']")
	WebElement likeCountText;

	@FindBy(xpath = "//button[@class='oxd-icon-button']/child::i[@class='oxd-icon bi-three-dots']")
	WebElement threedotsButton;

	@FindBy(xpath = "//p[text()='Edit Post']")
	WebElement editPostButton;

	@FindBy(xpath = "//p[text()='Edit Post']/following::textarea[@class='oxd-buzz-post-input']")
	WebElement editPOstCommentField;

	@FindBy(xpath = "//p[text()='Edit Post']/following::button[text()=' Post ']")
	WebElement EditandpostButton;

	@FindBy(xpath = "//p[text()='Delete Post']")
	WebElement deletePostButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")

	WebElement deleteConfirmButton;

	// page class constructer

	public BuzzPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);

	}

	// page operation methods

	public void navigateToBuzzPage() {

		ElementActions.clickElement(driver, buzzPageLink, scenario);
		WaitMethods.staticWait(5000, scenario);
	}

	public void postNewComment(String comment) {

		ElementActions.sendKeys(driver, commentField, scenario, comment);
		ElementActions.clickElement(driver, postButton, scenario);
		WaitMethods.staticWait(2000, scenario);

	}

	public HashMap<String, String> getCommentData() {

		HashMap<String, String> newlyAddedCommentData = new HashMap<String, String>();
		newlyAddedCommentData.put("user", ElementActions.getText(driver, posterName, scenario));
		newlyAddedCommentData.put("postTime", ElementActions.getText(driver, postedTime, scenario));
		newlyAddedCommentData.put("commentText", ElementActions.getText(driver, postedCommentText, scenario));

		return newlyAddedCommentData;
	}

	public void likeComment() {

		ElementActions.clickElement(driver, likeButton, scenario);
		WaitMethods.staticWait(2000, scenario);

	}

	public int getLikeCount() {

		return Integer.parseInt(ElementActions.getText(driver, likeCountText, scenario).split(" ")[0]);
	}

	public void editPost(String commentToUpdate) {
		ElementActions.clickElement(driver, threedotsButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, editPostButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.sendKeys(driver, editPOstCommentField, scenario, commentToUpdate);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, EditandpostButton, scenario);
	}

	public String getUpdatedComment() {
		return ElementActions.getText(driver, postedCommentText, scenario);
	}

	public void deleteComment() {
		ElementActions.clickElement(driver, threedotsButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, deletePostButton, scenario);
		WaitMethods.staticWait(2000, scenario);
		ElementActions.clickElement(driver, deleteConfirmButton, scenario);

	}

}
