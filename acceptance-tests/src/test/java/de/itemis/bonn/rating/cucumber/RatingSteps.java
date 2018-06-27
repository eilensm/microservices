package de.itemis.bonn.rating.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.itemis.bonn.rating.Rating;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class RatingSteps {

  private static final String URI = "http://localhost:8080/ratings";
  private static final int HTTP_STATUS_OK = 200;

  private Response response;

  @When("^the client creates a rating with value (\\d+)$")
  public void theClientCreatesARatingWithValue(final int value) {
    final Client client = ClientBuilder.newClient();
    response = client.target(URI)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(value, MediaType.APPLICATION_JSON));
  }

  @Then("^the client receives status code of (\\d+)$")
  public void theClientReceivesStatusCodeOf(final int statusCode) {
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }

  @And("^the client receives a rating object with a non empty id$")
  public void theClientReceivesARatingObjectWithANonEmptyId() {
    final Rating rating = response.readEntity(Rating.class);
    assertThat(rating.getId()).isNotEmpty();
  }

  @SuppressWarnings("Duplicates")
  @Given("^the rating service is available$")
  public void theRatingServiceIsAvailable() {
    try {
      final HttpURLConnection connection = (HttpURLConnection) new URL(URI).openConnection();
      connection.setRequestMethod("HEAD");
      final int responseCode = connection.getResponseCode();
      if (responseCode != HTTP_STATUS_OK) {
        fail("service is not available");
      }
    } catch (final IOException e) {
      fail("exception occured", e.getMessage());
    }
  }
}
