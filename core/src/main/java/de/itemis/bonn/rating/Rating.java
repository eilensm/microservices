package de.itemis.bonn.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

  private String id;
  private String customerName;
  private BigDecimal ratingPd;
  private String ratingClass;
}
