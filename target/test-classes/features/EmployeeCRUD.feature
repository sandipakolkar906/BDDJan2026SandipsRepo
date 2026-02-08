Feature: Employee CRUD and Search Operations

  @EmployeeCRUD
  Scenario Outline: I am able to add edit and search and delete the  employee
    Given Navigate to PIM after log in with Admin user
    When I Add employee with  first name as "<fName>" and mname as "<mName>" and lName as "<Lname>"
    Then I  verify employeeAdded in list with  first name as "<fName>" and mname as "<mName>" and lName as "<Lname>"
    When I click on Edit button and update below values and save the Data
      | updatefNamevalue | 1 |
      | updatedmName     | 1 |
      | updatedLname     | 1 |
    Then I search the employee and ensure that it is searched using below values
      | updatefNamevalue | AutUserFname1 |
      | updatedmName     | AutUserMname1 |
    And I select and Delete the Updated Employee and verify employee is not  in search result

    Examples: 
      | fName        | mName        | Lname        |
      | AutUserFname | AutUserMname | AutUserLname |
