package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String path) {
        response = restTemplate.getForEntity(path, String.class);
    }

    @Then("the response should be {string}")
    public void the_response_should_be(String expected) {
        assertEquals(expected, response.getBody());
    }
}
