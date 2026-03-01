package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.LoginPage;
import com.qa.pages.RecruitmentPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.DateUtils;
import com.qa.util.ReadProperties;
import com.qa.util.WaitMethods;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class RecruitmentCRUDSteps extends Base {

	Scenario scenario;

	LoginPage objLoginPage;
	RecruitmentPage objRecruitmentPage;

	String expectedfullName;

	@Before
	public void StartApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to Recruitment page after log in with Admin$")
	public void navigate_to_Recruitment_page_after_log_in_with_Admin() throws Throwable {

		scenario.write("Launching the application URL");

		driver = initializeWebDriver();
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("logging in to the applciation");
		objLoginPage = new LoginPage(driver, scenario);
		objLoginPage.login(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objRecruitmentPage= new RecruitmentPage(driver, scenario);
		scenario.write("Navigating to RecruitmentPage");

		objRecruitmentPage.navigateToRecruitmentPage();

		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^Add new Candidate with below field and values$")
	public void add_new_Candidate_with_below_field_and_values(DataTable newCandidateDateTable) throws Throwable {

		scenario.write("Adding a new Candidate !");

		expectedfullName = newCandidateDateTable.raw().get(1).get(1) + " " + newCandidateDateTable.raw().get(2).get(1)
				+ " " + newCandidateDateTable.raw().get(3).get(1);

		objRecruitmentPage.addNewCandidate(newCandidateDateTable.raw().get(1).get(1),
				newCandidateDateTable.raw().get(2).get(1), newCandidateDateTable.raw().get(3).get(1),
				newCandidateDateTable.raw().get(4).get(1));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify that candidate is added with currentDate$")
	public void i_verify_that_candidate_is_added_with_currentDate() throws Throwable {

		objRecruitmentPage.navigateToCandidateListTab();
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

		scenario.write("Verifying the candidate date and name  !");
		Assert.assertEquals(expectedfullName, objRecruitmentPage.getNewlyAddedCandiateName());
		Assert.assertEquals(DateUtils.getFormattedDate(), objRecruitmentPage.getNEwlyAddedCanddiateDate());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I delete the searched Candidate$")
	public void i_delete_the_searched_Candidate() throws Throwable {
		scenario.write("Deleting the newlya dded candidate  !");
		objRecruitmentPage.deleteNewlyAddedCandidate();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplication() {
		scenario.write("closing the application");
		closeBrowser();
	}

}
