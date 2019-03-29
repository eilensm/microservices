package de.itemis.bonn.rating.persistence.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "rating")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntity {

  @Id
  private String id;

  private String customerName;

  private BigDecimal ratingPd;

  private String ratingClass;
}
