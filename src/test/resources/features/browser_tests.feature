@browserTests
Feature: Browser TestCase


  Background: launch the profile web application and login with proper user
    Given Profile login page
    When user logged in to profile home page
      | user-name   | password      |
      | pcops_user1 | $uperAdm1n |

  @PCOPS-27
  @actionItems
  Scenario: Action items for Selection
    Given Definition Page
    When the User clicks on Select
    Then the following actions appear
      | View Definition     |
      | Download Definition |
      | Map Definition      |
      | View Template       |
      | Edit Template       |
