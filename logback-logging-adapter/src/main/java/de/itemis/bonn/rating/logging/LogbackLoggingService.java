package de.itemis.bonn.rating.logging;

import de.itemis.bonn.rating.spi.LoggingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogbackLoggingService implements LoggingService {

  @Override
  public void logEvent(final String message) {
    log.info(message);
  }
}
