package otus.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import otus.config.PropertiesConfig;
import otus.config.WebDriverConfig;

@CucumberContextConfiguration
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
