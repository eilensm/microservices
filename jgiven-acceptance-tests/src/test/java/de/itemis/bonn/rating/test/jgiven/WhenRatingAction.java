package de.itemis.bonn.rating.test.jgiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WhenRatingAction extends Stage<WhenRatingAction> {

  private static final String URI = "http://localhost:8080/ratings";

  @ProvidedScenarioState
  private Response response;

  public WhenRatingAction theClientCreatesARatingWithValue(int ratingValue) {
    final Client client = ClientBuilder.newClient();
    response = client.target(URI)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(ratingValue, MediaType.APPLICATION_JSON));
    return self();
  }
}
