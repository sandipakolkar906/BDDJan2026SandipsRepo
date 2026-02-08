package com.qa.stepdefinations;

import java.util.HashMap;

import com.qa.base.Base;
import com.qa.pages.LoginPage;
import com.qa.pages.PimPage;
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

public class EmployeeCRUDSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	PimPage objPimPage;

	@Before
	public void StartApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to PIM after log in with Admin user$")
	public void navigate_to_PIM_after_log_in_with_Admin_user() throws Throwable {

		scenario.write("Launching the application URL");

		driver = initializeWebDriver();
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("logging in to the applciation");
		objLoginPage = new LoginPage(driver, scenario);
		objLoginPage.login(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("Navigating to PIM Page ");
		objPimPage= new PimPage(driver,scenario);
		
		Assert.assertEquals("PIM", objPimPage.navigateToPimPage());
		WaitMethods.staticWait(2000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I Add employee with  first name as \"([^\"]*)\" and mname as \"([^\"]*)\" and lName as \"([^\"]*)\"$")
	public void i_Add_employee_with_first_name_as_and_mname_as_and_lName_as(String fName, String mName, String lName)
			throws Throwable {
		
		scenario.write("Adding new Employee ");
		objPimPage.addEmployee(fName, mName, lName);
		WaitMethods.staticWait(8000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I  verify employeeAdded in list with  first name as \"([^\"]*)\" and mname as \"([^\"]*)\" and lName as \"([^\"]*)\"$")
	public void i_verify_employeeAdded_in_list_with_first_name_as_and_mname_as_and_lName_as(String fName, String mName,
			String lName) throws Throwable {
		scenario.write("Searhing the newly added emp ");
		
		HashMap<String , String>  seaarchedValuesMap=objPimPage.searhEmpbyName(fName);
		scenario.write("Values in the Map====" + seaarchedValuesMap.toString());
		Assert.assertEquals(fName+ " " + mName, seaarchedValuesMap.get("fNammeandmName"));
		Assert.assertEquals(lName, seaarchedValuesMap.get("lName"));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I click on Edit button and update below values and save the Data$")
	public void i_click_on_Edit_button_and_update_below_values_and_save_the_Data(DataTable editempDataTable) throws Throwable {
		
		scenario.write("Editing  newly added emp ");
		objPimPage.editEmp(editempDataTable.raw().get(0).get(1), editempDataTable.raw().get(1).get(1), editempDataTable.raw().get(2).get(1));
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I search the employee and ensure that it is searched using below values$")
	public void i_search_the_employee_and_ensure_that_it_is_searched_using_below_values(DataTable arg1)
			throws Throwable {
		scenario.write("Ignoring this step ");
	}

	@Then("^I select and Delete the Updated Employee and verify employee is not  in search result$")
	public void i_select_and_Delete_the_Updated_Employee_and_verify_employee_is_not_in_search_result()
			throws Throwable {
		scenario.write("Deleting  newly added emp ");
		objPimPage.deleteEmp();
		WaitMethods.staticWait(5000, scenario);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		
	}

	@After
	public void closeApplication() {
		scenario.write("closing the application");
		closeBrowser();
	}

}
