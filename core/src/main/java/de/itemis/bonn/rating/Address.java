package de.itemis.bonn.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Address {

  private String street;

  private String houseNo;

  private String postalCode;
}
