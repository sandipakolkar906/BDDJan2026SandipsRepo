package com.qa.stepdefinations;

import java.util.HashMap;

import com.qa.base.Base;
import com.qa.pages.DashboardPage;
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
import junit.framework.Assert;

public class DashboardCRUDSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	DashboardPage objDashboardPage;

	@Before
	public void StartApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^I log in with Admin user and I am at Dashboard Page$")
	public void i_log_in_with_Admin_user_and_I_am_at_Dashboard_Page() throws Throwable {
		scenario.write("Launching the application URL");

		driver = initializeWebDriver();
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("logging in to the applciation");
		objLoginPage = new LoginPage(driver, scenario);
		objLoginPage.login(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I view the Time at work widget at Dashboard Page$")
	public void i_view_the_Time_at_work_widget_at_Dashboard_Page() throws Throwable {

		scenario.write("veryfying the title of the widget");

		objDashboardPage = new DashboardPage(driver, scenario);

		Assert.assertEquals("Time at Work", objDashboardPage.getTimeatworWidgetData().get("WidgetTitle"));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I check below values from the widget showing correct values$")
	public void i_check_below_values_from_the_widget_showing_correct_values(DataTable expectedValueTable)
			throws Throwable {

		HashMap<String, String> expectedValueMap = new HashMap<String, String>();
		expectedValueMap.put("WidgetTitle", expectedValueTable.raw().get(1).get(1));
		expectedValueMap.put("lastpunchedinTime", expectedValueTable.raw().get(2).get(1));
		expectedValueMap.put("currentTime", expectedValueTable.raw().get(3).get(1));
		expectedValueMap.put("CurrentWeekspan", expectedValueTable.raw().get(4).get(1));
		expectedValueMap.put("TotalWeekHoursmins", expectedValueTable.raw().get(5).get(1));

		System.out.println(" Expetced Valye map is :" + expectedValueMap);

		System.out.println(" Actual Values Map from UI : " + objDashboardPage.getTimeatworWidgetData());

		Assert.assertEquals(expectedValueMap, objDashboardPage.getTimeatworWidgetData());

	}

	@After
	public void closeApplication() {
		scenario.write("closing the application");
		closeBrowser();
	}

}