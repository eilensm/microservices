package de.itemis.bonn.rating.test.jgiven;

import com.tngtech.jgiven.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.fail;

public class GivenRatingState extends Stage<GivenRatingState> {

  private static final String URI = "http://localhost:8080/ratings";
  private static final int HTTP_STATUS_OK = 200;

  @SuppressWarnings("Duplicates")
  public GivenRatingState ratingServiceIsAvailable() {
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
    return self();
  }
}
