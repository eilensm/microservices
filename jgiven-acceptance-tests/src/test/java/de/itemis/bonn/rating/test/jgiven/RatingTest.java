package de.itemis.bonn.rating.test.jgiven;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class RatingTest
    extends ScenarioTest<GivenRatingState, WhenRatingAction, ThenRatingOutcome> {

  @Test
  public void testCreateRating() {
    given().ratingServiceIsAvailable();
    when().theClientCreatesARatingWithValue(4711);
    then().theClientReceivesStatusCodeOf(201)
        .and().theClientReceivesARatingObjectWithANonEmptyId();
  }
}
