package de.itemis.bonn.rating.persistence.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

  private String streetAndHouseNo;

  private int postalCode;
}
