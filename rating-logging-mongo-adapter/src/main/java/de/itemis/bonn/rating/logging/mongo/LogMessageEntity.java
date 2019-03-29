package de.itemis.bonn.rating.logging.mongo;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "message")
public class LogMessageEntity {

  private String id;

  private String message;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LogMessageEntity that = (LogMessageEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, message);
  }
}
