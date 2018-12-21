package de.itemis.bonn.rating;

import de.itemis.bonn.rating.spi.LoggingService;
import de.itemis.bonn.rating.spi.RatingPersistenceService;

import java.util.List;

public class RatingService {

  private final RatingPersistenceService ratingPersistenceService;
  private final LoggingService loggingService;

  public RatingService(final RatingPersistenceService ratingPersistenceService, final LoggingService loggingService) {
    this.ratingPersistenceService = ratingPersistenceService;
    this.loggingService = loggingService;
  }

  public Rating createRating(final int value) {
    loggingService.logEvent("creating a rating object with value " + value);
    final Rating rating = Rating.builder().value(value).build();
    return ratingPersistenceService.storeRating(rating);
  }

  public Rating rate(final Rating rating) {
    final Rating storedRating = ratingPersistenceService.findRatingById(rating.getId());
    if (storedRating == null) {
      throw new RatingException("rating not found");
    }
    storedRating.setValue(rating.getValue());
    return ratingPersistenceService.storeRating(storedRating);
  }

  public Rating getRating(final String ratingId) {
    return ratingPersistenceService.findRatingById(ratingId);
  }

  public List<Rating> getRatings() {
    return ratingPersistenceService.findAllRatings();
  }
}
