@regression
Feature: Amber Student Test Automation Assesment

  Background: Open Browser & URL
    Given Open Browser and visit URL

  @sanity
  Scenario: Task 1
    Given User is on Company Site
    When User Search for accommodation in "London"
    And Filter search results for Room Type "Private Room" and Sharing "Private Bathroom"
    And Open the second search result in a new tab
    And Switch to the tab
    Then Assert the title and location of the property

  @sanity
  Scenario: Task 2
    Given User is on Company Site
    When Click on Search bar
    Then Verify list of popular cities is different for all countries and no city is repeated or mentioned in more than one country tab by Iterate over the countries tab

  @sanity @smoke
  Scenario: Task 3
    Given User is on Company Site
    When Login using Gmail
    And User Search for accommodation in "London"
    And Filter search results for Room Type "Private Room" and Sharing "Private Bathroom"
    And Open the second search result in a new tab
    And Switch to the tab
    And Shortlist the second search result
    Then Assert the title and location of the property
    And Verify the property is shortlisted
    And Log out of your account
    Then Verify Logout sucessfully
