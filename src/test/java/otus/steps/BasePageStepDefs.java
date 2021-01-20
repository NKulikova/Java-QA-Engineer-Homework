package otus.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.After;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import otus.config.Config;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = Config.class)
public class BasePageStepDefs {

    @Before
    public void beforeMethod() { System.out.println("before method"); }

    @After
    public void afterMethod() { System.out.println("after method"); }
}
