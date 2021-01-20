package otus;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/otus"},
        glue = {"otus.steps"},
        tags = "@test"
)

public class RunTest {
}
