package de.itemis.bonn.rating;

import de.itemis.bonn.rating.spi.LoggingService;
import de.itemis.bonn.rating.spi.RatingPersistenceService;

import java.math.BigDecimal;
import java.util.List;

public class RatingService {

  private final RatingPersistenceService ratingPersistenceService;
  private final LoggingService loggingService;

  public RatingService(final RatingPersistenceService ratingPersistenceService, final LoggingService loggingService) {
    this.ratingPersistenceService = ratingPersistenceService;
    this.loggingService = loggingService;
  }

  public Rating createRating(final String customerName, final BigDecimal ratingPd, final String ratingClass) {
    loggingService.logEvent(String.format("creating a rating object for customer %s with values (%s/%s)", customerName,
        ratingPd != null ? ratingPd.toString() : null, ratingClass));
    final Rating rating = Rating.builder()
        .customerName(customerName)
        .ratingPd(ratingPd)
        .ratingClass(ratingClass)
        .build();
    return ratingPersistenceService.storeRating(rating);
  }

  public Rating getRating(final String ratingId) {
    return ratingPersistenceService.findRatingById(ratingId);
  }

  public List<Rating> getRatings() {
    return ratingPersistenceService.findAllRatings();
  }
}
