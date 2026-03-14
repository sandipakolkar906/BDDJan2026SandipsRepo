Feature: Buzz CRUD Operations

  @BuzzCRUDTest
  Scenario Outline: I am able to add edit and  delete the  comments
    Given Navigate to BUzz after log in with Admin user
    When Post the comment as "<Comment>"
    Then verify that comment time user and comment text is posted correctly as "<Comment>"
    When I click on like
    Then I verify the like count is updated as Expected count "<likecount>"
    When I Edit the post with updated comment"<updatedComment>"
    Then I verify that updated comment"<updatedComment>"
    Then I delete the Post

    Examples: 
      | Comment                         | likecount | updatedComment |
      | OrangeHRM is very good software |         1 | sandip         |