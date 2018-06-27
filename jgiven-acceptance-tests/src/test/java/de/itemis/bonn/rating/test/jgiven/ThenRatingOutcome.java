package de.itemis.bonn.rating.test.jgiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import de.itemis.bonn.rating.Rating;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenRatingOutcome extends Stage<ThenRatingOutcome> {

  @ExpectedScenarioState
  private Response response;

  public ThenRatingOutcome theClientReceivesStatusCodeOf(final int statusCode) {
    assertThat(response.getStatus()).isEqualTo(statusCode);
    return self();
  }

  public ThenRatingOutcome theClientReceivesARatingObjectWithANonEmptyId() {
    final Rating rating = response.readEntity(Rating.class);
    assertThat(rating.getId()).isNotEmpty();
    return self();
  }
}
