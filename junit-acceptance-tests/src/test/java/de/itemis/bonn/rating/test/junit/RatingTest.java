package de.itemis.bonn.rating.test.junit;

import de.itemis.bonn.rating.Rating;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class RatingTest {

  private static final String URI = "http://localhost:8080/ratings";

  private final int expectedStatusCode;
  private final int value;

  public RatingTest(final int expectedStatusCode, final int value) {
    this.expectedStatusCode = expectedStatusCode;
    this.value = value;
  }

  @Parameterized.Parameters
  public static Collection testData() {
    return Arrays.asList(new Object[][]{
        {201, 4711},
        {201, 4712},
    });
  }

  @Test
  public void createRating() {
    final Client client = ClientBuilder.newClient();
    final Response response = client.target(URI)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(value, MediaType.APPLICATION_JSON));
    assertThat(response.getStatus()).isEqualTo(expectedStatusCode);
    final Rating rating = response.readEntity(Rating.class);
    assertThat(rating.getId()).isNotEmpty();
  }
}
