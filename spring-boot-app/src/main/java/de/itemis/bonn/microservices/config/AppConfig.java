package de.itemis.bonn.microservices.config;

import de.itemis.bonn.rating.RatingService;
import de.itemis.bonn.rating.logging.mongo.MongoLoggingService;
import de.itemis.bonn.rating.logging.mongo.MongoMessageRepository;
import de.itemis.bonn.rating.persistence.mongo.MongoRatingPersistenceService;
import de.itemis.bonn.rating.persistence.mongo.MongoRatingRepository;
import de.itemis.bonn.rating.spi.LoggingService;
import de.itemis.bonn.rating.spi.RatingPersistenceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
    "de.itemis.bonn.rating.persistence.mongo",
    "de.itemis.bonn.rating.logging.mongo"})
public class AppConfig {

  @Bean
  public RatingPersistenceService ratingPersistenceService(final MongoRatingRepository ratingRepository) {
    return new MongoRatingPersistenceService(ratingRepository);
  }

  @Bean
  public LoggingService loggingService(final MongoMessageRepository mongoMessageRepository) {
    return new MongoLoggingService(mongoMessageRepository);
  }

//  @Bean
//  public LoggingService logbackLoggingService() {
//    return new LogbackLoggingService();
//  }

  @Bean
  public RatingService ratingService(
      final RatingPersistenceService ratingPersistenceService, final LoggingService loggingService) {
    return new RatingService(ratingPersistenceService, loggingService);
  }
}
