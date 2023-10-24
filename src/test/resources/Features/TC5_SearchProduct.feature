@SearchProduct
Feature: Product Module API Automation

  Scenario Outline: Verify search product to the application through api
    Given User add headers for to search product
    When User add request body for search product "<productName>"
    And User send "POST" request for search product endpoint
    Then User should verify the status code is 200
    And User verify the search product response message matches "OK"

    Examples: 
      | productName |
      | nuts        |
