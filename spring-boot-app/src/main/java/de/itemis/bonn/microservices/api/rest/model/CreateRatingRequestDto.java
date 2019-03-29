package de.itemis.bonn.microservices.api.rest.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateRatingRequestDto {
  private String customerName;
  private BigDecimal ratingPd;
  private String ratingClass;
}
