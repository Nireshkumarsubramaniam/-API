@State
Feature: State Module API Automation

Scenario: Verify User Get StateList through api
Given User add headers for to stateList
When User send "GET" request for stateList endpoint
Then User should verify the status code is 200
And User should verify the stateList responce message match "Tamil Nadu" and saved stateid
 
 