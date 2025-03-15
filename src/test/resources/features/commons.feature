Feature: User tries to use the filter

@filterAtoZ
Scenario: Successfully using filter A to Z
    Given I open browser and login with valid credentials
    When I use the filter A to Z
    Then Products are successfully filtered from A to Z

@filterZtoA
Scenario: Successfully using filter Z to A
    Given I open browser and login with valid credentials
    When I use the filter Z to A
    Then Products are successfully filtered from Z to A