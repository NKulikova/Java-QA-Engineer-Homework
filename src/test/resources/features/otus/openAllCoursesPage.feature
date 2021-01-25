Feature: Open all courses
#  @test
  Scenario: User click button "More courses"
    Given Open Main page
    And Close Banner
    And Click More courses
    Then Check open page with courses