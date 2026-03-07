package com.qa.stepdefinations;

import org.junit.Assert;

import com.qa.base.Base;
import com.qa.pages.ClaimsPage;
import com.qa.pages.LoginPage;
import com.qa.util.CaptureScreenshot;
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

public class ClaimsCRUDSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	ClaimsPage objClaimsPage;

	@Before
	public void StartApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to Claims after log in with Admin user$")
	public void navigate_to_Claims_after_log_in_with_Admin_user() throws Throwable {

		scenario.write("Launching the application URL");

		driver = initializeWebDriver();
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("logging in to the applciation");
		objLoginPage = new LoginPage(driver, scenario);
		objLoginPage.login(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

		scenario.write("Navigate to Claims Page");
		objClaimsPage = new ClaimsPage(driver, scenario);
		objClaimsPage.navigateToClaimsPage();

		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I navigate to Configuration menu and Select Event option$")
	public void i_navigate_to_Configuration_menu_and_Select_Event_option() throws Throwable {

		scenario.write("navigate to Configuration menu and Select Event option");
		objClaimsPage.navigateToEventsOption();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I add new Event with below event Name$")
	public void i_add_new_Event_with_below_event_Name(DataTable EventNameTable) throws Throwable {
		scenario.write("add new Event with below event Name");
		objClaimsPage.addNewEvent(EventNameTable.raw().get(0).get(1));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I Search Event with below eventName$")
	public void i_Search_Event_with_below_eventName(DataTable EventNameTable) throws Throwable {
		scenario.write("Search Event with below eventName");

		Assert.assertEquals(EventNameTable.raw().get(0).get(1),
				objClaimsPage.searchnewlyAddedEventByName(EventNameTable.raw().get(0).get(1)));

		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I edit the Event and change name$")
	public void i_edit_the_Event_and_change_name(DataTable EventNameTable) throws Throwable {
		scenario.write("Edit Event Name");

		objClaimsPage.editEventName(EventNameTable.raw().get(0).get(1));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I delete the newly added and updated Event$")
	public void i_delete_the_newly_added_and_updated_Event() throws Throwable {
		scenario.write("Delete Event ");
		objClaimsPage.deletenewlyAddedEvent();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplication() {
		scenario.write("closing the application");
		closeBrowser();
	}

}
