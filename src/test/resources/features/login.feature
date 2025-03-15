Feature: Login functionality

@loginValid
Scenario: User login with valid credentials
    Given I open the browser
    And I navigate to the login page
    When I enter valid credentials
    Then I should be logged in successfully

@loginInvalid
Scenario: User login with invalid credentials
  Given I open the browser
  And I navigate to the login page
  When I enter invalid credentials
  Then I should see an error message

@loginwithoutInputUsername
Scenario: User login without input username
  Given I open the browser
  And I navigate to the login page
  When I enter blank username
  Then I should see an error message for blank username

@loginwithoutInputPassword
  Scenario: User login without input password
  Given I open the browser
  And I navigate to the login page
  When I enter blank password
  Then I should see an error message for blank password