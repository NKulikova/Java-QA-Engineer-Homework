package otus.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import otus.config.WebDriverConfig;
import otus.pages.LoginPage;
import otus.pages.MainPage;

public class LoginPageStepDefs {

    private static Logger logger = LogManager.getLogger(LoginPageStepDefs.class);

    @Autowired
    private LoginPage loginPage;

    @Value("${url}")
    private String url;
    @Value("${urlProgramming}")
    private String urlProgramming;

    @And("Enter login {string}")
    public void enterLogin(String login) {
        loginPage.enterLogin(login);
    }

    @And("Enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("Click Submit")
    public void clickSubmit() {
        loginPage.clickSubmit();
    }

    @Then("Check open Personal Area")
    public void checkOpenPersonalArea() {

    }

    @Then("Check Error message {string}")
    public void checkErrorMessage(String message) {
        Assertions.assertTrue(loginPage.getErrorMessage().contains(message));
    }
}
