Feature: Claims CRUD operations for recommit

  @EventCRUD
  Scenario: I am able to add view edit and Delete  Event
    Given Navigate to Claims after log in with Admin user
    When I navigate to Configuration menu and Select Event option
    When I add new Event with below event Name
      | EventName | AnnualParty |
    Then I Search Event with below eventName
      | EventName | AnnualParty |
    When I edit the Event and change name
      | ChangedEventName | abc |
    Then I delete the newly added and updated Event

  @ClaimsCRUD
  Scenario: I am able to add, edit and Delete  Expenses and verify the total
    Given Navigate to Claims after log in with Admin user
    And I navigate to submit claims Page
    When I crate claim Request with Event and Currency
      | EventName  | currencyName        |
      | HotelBills | Afghanistan Afghani |
    When I add Expenses for the new claim Request with below fields and values
      | Field         | values      |
      | ExpenseType   | FuelExpense |
      | Date          | 2025-04-06  |
      | ExpenseAmount |          50 |
    When I add Expenses for the new claim Request with below fields and values
      | Field         | values      |
      | ExpenseType   | FuelExpense |
      | Date          | 2025-04-06  |
      | ExpenseAmount |          50 |
    Then I verify the total amount of all expenses is caculated correctly
