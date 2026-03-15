Feature: Employee Reports CRUD and Search Operations

  @EmpReports
  Scenario Outline: I am able to add edit search generate and delete  the employee report
    Given Navigate to PIM after log in with Admin user
    When I add Custom Report with below ReportName as "<ReportName>" and below Display field group and field names
      | Displayfieldgroup | Personal            |
      | Field1Title       | Employee Id         |
      | Field2Title       | Employee First Name |
      | Field3Title       | Employee Last Name  |
    Then I  verify Report is searched in the Report with ReportName as "<ReportName>"
    And I verify the Report is generated with below fields
      | Field1Title | Employee Id         |
      | Field2Title | Employee Last Name  |
      | Field3Title | Employee First Name |

    Examples: 
      | ReportName    |
      | SampleReport2 |
