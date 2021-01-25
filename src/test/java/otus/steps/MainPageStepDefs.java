package otus.steps;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import otus.config.WebDriverConfig;
import otus.pages.MainPage;

public class MainPageStepDefs {

    private static Logger logger = LogManager.getLogger(MainPageStepDefs.class);

    @Autowired
    private MainPage mainPage;

    @Value("${url}")
    private String url;
    @Value("${urlProgramming}")
    private String urlProgramming;

    @Given("Open Main page")
    public void openMainPage() {
        mainPage.open(url);
        logger.info("open page " + url);
    }

    @When("Close Cookies Popup")
    public void closeCookiesPopup() {
        mainPage.closeCookiesPopup();
    }

    @When("Close Banner")
    public void closeBanner() {
        mainPage.closeBanner();
    }

    @When("Click More courses")
    public void openMoreCourses() {
        mainPage.openMoreCourses();
        logger.info("open Courses list");
    }

    @Then("Check open page with courses")
    public void chekPage() {
        Assertions.assertEquals(urlProgramming, WebDriverConfig.firefoxDriver().getCurrentUrl());
    }

    @When("Open Login form")
    public void openLoginForm() {
        mainPage.openLoginForm();
    }
}
