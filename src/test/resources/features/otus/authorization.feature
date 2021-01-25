Feature: User authorization
  @test
  Scenario:
    Given Open Main page
    When Open Login form
    And Enter login "f_19_natali@mail.ru"
    And Enter password "QWE123rty"
    And Click Submit
    Then Check Error message "Такая пара логин/пароль не существует"