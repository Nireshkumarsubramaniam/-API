
Feature: Address Module API Automation
@AddAddress
  Scenario Outline: Verify add user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body for add new address "<firstname>","<lastname>","<mobile>","<appartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the addUserAddress response message matches "Address added successfully"

    Examples: 
      | firstname   | lastname    | mobile     | appartment | state | city | country | zipcode | address | address_type |
      | Nireshkumar | Subramaniam | 94868498498| star       |    35 | 4440 |     101 |  600121 | chennai | Home         |
@UpdateAddress
  Scenario Outline: Verify update user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body for update the address "<first_name>","<last_name>","<mobile>","<appartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>" with address id
    And User send "PUT" request for updateUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the updateUserAddress response message matches "Address updated successfully"

    Examples: 
      | first_name  | last_name   | mobile     | appartment | state | city | country | zipcode | address | address_type |
      | Nireshkumar | Subramaniam | 9965263222 | star       |    35 | 4440 |     101 |  600096 | erode   | Home         |
@DeleteAddress
  Scenario: Verify delete user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body for delete the address with address id
    And User send "DELETE" request for deleteUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the deleteUserAddress response message matches "Address deleted successfully"
@GetAddress
  Scenario: Verify get user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User send "GET" request for getUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the getUserAddress response message matches "Chennai" and saved city name
