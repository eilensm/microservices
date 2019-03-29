package de.itemis.bonn.rating.logging.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoMessageRepository extends MongoRepository<LogMessageEntity, String> {
}
