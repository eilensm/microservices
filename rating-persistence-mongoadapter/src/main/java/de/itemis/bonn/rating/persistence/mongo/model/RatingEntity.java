package de.itemis.bonn.rating.persistence.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class RatingEntity {

  private String id;

  private int value;
}
