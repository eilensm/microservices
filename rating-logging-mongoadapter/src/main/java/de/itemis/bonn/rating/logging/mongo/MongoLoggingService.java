package de.itemis.bonn.rating.logging.mongo;

import de.itemis.bonn.rating.spi.LoggingService;

public class MongoLoggingService implements LoggingService {

  private MongoMessageRepository mongoMessageRepository;

  public MongoLoggingService(MongoMessageRepository mongoMessageRepository) {
    this.mongoMessageRepository = mongoMessageRepository;
  }

  @Override
  public void logEvent(String message) {
    LogMessageEntity messageEntity = new LogMessageEntity();
    messageEntity.setMessage(message);
    mongoMessageRepository.save(messageEntity);
  }
}
