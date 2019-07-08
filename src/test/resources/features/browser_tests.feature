@browserTests
Feature: Browser TestCase


  Background: launch the web application and login with proper user
    Given login page
    When user logged in to profile home page
      | user-name   | password      |
      | user**| pass** |


  @actionItems
  Scenario: Action items for Selection
    Given Definition Page
    When the User clicks on Select
    Then the following actions appear
      | View      |
      | Download  |
      | Map       |
      | View      |
      | Edit      |
