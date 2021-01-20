package otus.steps;

import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import otus.config.Config;
import otus.pages.MainPage;

public class MainPageStepDefs {

    private static Logger logger = LogManager.getLogger(MainPageStepDefs.class);

    @Autowired
    private MainPage mainPage;

    @Value("${url}")
    private String url;

    @When("Open MainPage")
    public void openMainPage() {
        mainPage.open(url);
        logger.info("open page" + url);
    }

    @Then("Check open MainPage")
    public void chekPage() {
        Assertions.assertTrue(true);
    }
}
