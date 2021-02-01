Feature: User authorization
  @test
  Scenario: User enter wrong password
    Given Open Main page
    When Open Login form
    And Enter login "f_19_natali@mail.ru"
    And Enter password "123"
    And Click Submit
    Then Check Error message "Такая пара логин/пароль не существует"