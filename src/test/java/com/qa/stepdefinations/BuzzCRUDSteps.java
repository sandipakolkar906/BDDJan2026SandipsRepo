package com.qa.stepdefinations;

import java.time.Clock;

import com.qa.base.Base;
import com.qa.pages.BuzzPage;
import com.qa.pages.ClaimsPage;
import com.qa.pages.LoginPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.DateUtils;
import com.qa.util.ReadProperties;
import com.qa.util.WaitMethods;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import java.time.Clock;

public class BuzzCRUDSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;

	BuzzPage objBuzzPage;
	String firstComment;

	@Before
	public void StartApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to BUzz after log in with Admin user$")
	public void navigate_to_BUzz_after_log_in_with_Admin_user() throws Throwable {
		scenario.write("Launching the application URL");

		driver = initializeWebDriver();
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("logging in to the applciation");
		objLoginPage = new LoginPage(driver, scenario);
		objLoginPage.login(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("Navigating to Buzz page");

		objBuzzPage = new BuzzPage(driver, scenario);
		objBuzzPage.navigateToBuzzPage();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^Post the comment as \"([^\"]*)\"$")
	public void post_the_comment_as(String comment) throws Throwable {
		scenario.write("PostingComment");
		firstComment=comment;
		objBuzzPage.postNewComment(comment);
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^verify that comment time user and comment text is posted correctly as \"([^\"]*)\"$")
	public void verify_that_comment_time_user_and_comment_text_is_posted_correctly_as(String expectedComment)
			throws Throwable {
		scenario.write("Verifying comment Details");
		System.out.println("Actual Data fetched from page :" + objBuzzPage.getCommentData());
		Assert.assertEquals("sandip akolkar", objBuzzPage.getCommentData().get("user"));
		//Assert.assertEquals(DateUtils.getFormattedDateTime(), objBuzzPage.getCommentData().get("postTime"));
		Assert.assertEquals(expectedComment, objBuzzPage.getCommentData().get("commentText"));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I click on like$")
	public void i_click_on_like() throws Throwable {
		scenario.write("likeing the comment ");
		objBuzzPage.likeComment();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify the like count is updated as Expected count \"([^\"]*)\"$")
	public void i_verify_the_like_count_is_updated_as_Expected_count(String expectedLikeCount) throws Throwable {
		scenario.write("Verifyng the like count  ");
		Assert.assertEquals(Integer.parseInt(expectedLikeCount), objBuzzPage.getLikeCount());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I Edit the post with updated comment\"([^\"]*)\"$")
	public void i_Edit_the_post_with_updated_comment(String commentToUpdate) throws Throwable {
		scenario.write("Editing the comment   ");
		objBuzzPage.editPost(commentToUpdate);
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I verify that updated comment\"([^\"]*)\"$")
	public void i_verify_that_updated_comment(String expectedUpdatedComment) throws Throwable {
		scenario.write("Verifying edited comment   ");
		Assert.assertEquals(firstComment+expectedUpdatedComment, objBuzzPage.getUpdatedComment());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I delete the Post$")
	public void i_delete_the_Post() throws Throwable {
		scenario.write("Deleting the post   ");
		objBuzzPage.deleteComment();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@After
	public void closeApplication() {
		scenario.write("closing the application");
		closeBrowser();
	}

}
