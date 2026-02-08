Feature: Dashboard Widgets tests

  @DashboardTimeWidge
  Scenario: I am able to view correct information at Time at Work widget
    Given I log in with Admin user and I am at Dashboard Page
    When I view the Time at work widget at Dashboard Page
    Then I check below values from the widget showing correct values
      | WidgetinfoTitle    | ExpectedValue   |
      | WidgetTitle        | Time at Work    |
      | lastpunchedinTime  | Not Punched In  |
      | currentTime        | 0h 0m Today     |
      | CurrentWeekspan    | Feb 23 - Mar 01 |
      | TotalWeekHoursmins | 0h 0m           |
