package de.itemis.bonn.rating;

import de.itemis.bonn.rating.spi.LoggingService;
import de.itemis.bonn.rating.spi.RatingPersistenceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTest {

  private static final String RATING_ID = "89xosdfjew0";
  private static final String CUSTOMER_NAME = "Meier";
  private static final BigDecimal RATING_PD = BigDecimal.valueOf(33.66);
  private static final String RATING_CLASS = "1a";

  private static final String RATING2_ID = "Bxj8908de";
  private static final String CUSTOMER_NAME2 = "Meier";
  private static final BigDecimal RATING_PD2 = BigDecimal.valueOf(47.11);
  private static final String RATING_CLASS2 = "2b";

  @Mock
  private RatingPersistenceService ratingPersistenceService;

  @Mock
  private LoggingService loggingService;

  @InjectMocks
  private RatingService ratingService;

  @Before
  public void setUp() {
    when(ratingPersistenceService.storeRating(any(Rating.class))).then(invocation -> {
      final Rating input = invocation.getArgument(0);
      final Rating answer = Rating.builder().build();
      answer.setId(input.getId() != null ? input.getId() : RATING_ID);
      answer.setCustomerName(input.getCustomerName());
      answer.setRatingClass(input.getRatingClass());
      answer.setRatingPd(input.getRatingPd());
      return answer;
    });
    final Rating rating1 = buildRating(RATING_ID, CUSTOMER_NAME, RATING_PD, RATING_CLASS);
    final Rating rating2 = buildRating(RATING2_ID, CUSTOMER_NAME2, RATING_PD2, RATING_CLASS2);
    when(ratingPersistenceService.findRatingById(RATING_ID)).thenReturn(rating1);
    when(ratingPersistenceService.findAllRatings()).thenReturn(
        asList(rating1, rating2));
  }

  @Test
  public void createRatingShouldLog() {
    ratingService.createRating(CUSTOMER_NAME, RATING_PD, RATING_CLASS);
    verify(loggingService).logEvent("creating a rating object for customer Meier with values (33.66/1a)");
  }

  @Test
  public void createRatingWithNullPdShouldLogNullForPd() {
    ratingService.createRating(CUSTOMER_NAME, null, RATING_CLASS);
    verify(loggingService).logEvent("creating a rating object for customer Meier with values (null/1a)");
  }

  @Test
  public void createRatingShouldStoreRating() {
    ratingService.createRating(CUSTOMER_NAME, RATING_PD, RATING_CLASS);
    final ArgumentCaptor<Rating> captor = ArgumentCaptor.forClass(Rating.class);
    verify(ratingPersistenceService).storeRating(captor.capture());
    assertThat(captor.getValue())
        .extracting(Rating::getCustomerName, Rating::getRatingPd, Rating::getRatingClass)
        .contains(CUSTOMER_NAME, RATING_PD, RATING_CLASS);
  }

  @Test
  public void getRatingShouldQueryPersistence() {
    ratingService.getRating(RATING_ID);
    verify(ratingPersistenceService).findRatingById(RATING_ID);
  }

  @Test
  public void getRatingShouldReturnTheRating() {
    final Rating rating = ratingService.getRating(RATING_ID);
    assertThat(rating).isNotNull();
    assertThat(rating).isEqualTo(buildRating(RATING_ID, CUSTOMER_NAME, RATING_PD, RATING_CLASS));
  }

  @Test
  public void getRatingShouldReturnNullIfNotFound() {
    when(ratingPersistenceService.findRatingById(RATING_ID)).thenReturn(null);
    assertThat(ratingService.getRating(RATING_ID)).isNull();
  }

  @Test
  public void getRatingsShouldQueryPersistence() {
    ratingService.getRatings();
    verify(ratingPersistenceService).findAllRatings();
  }

  @Test
  public void getRatingsShouldReturnAllExistingRatings() {
    final List<Rating> ratings = ratingService.getRatings();
    assertThat(ratings).hasSize(2);
  }

  private static Rating buildRating(final String id, final String customerName, final BigDecimal ratingPd,
                                    final String ratingClass) {
    return Rating.builder()
        .id(id)
        .customerName(customerName)
        .ratingPd(ratingPd)
        .ratingClass(ratingClass)
        .build();
  }
}
