package otus.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import otus.config.PropertiesConfig;
import otus.config.WebDriverConfig;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = PropertiesConfig.class)
public class BasePageStepDefs {

    @Before
    public void beforeMethod() { System.out.println("before method"); }

    @After
    public void afterMethod() {
        System.out.println("after method");
        WebDriverConfig.firefoxDriver().quit();
    }
}
