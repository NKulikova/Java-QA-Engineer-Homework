import io.cucumber.java.en.*;
import org.springframework.http.HttpStatus;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefsIntegrationTest {

    @When("^the client calls /version$")
    public void client_calls_GET_version() {
        executeGet("http://localhost:8082/version");
    }
}
