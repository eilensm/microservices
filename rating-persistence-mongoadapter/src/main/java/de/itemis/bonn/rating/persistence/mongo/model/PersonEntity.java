package de.itemis.bonn.rating.persistence.mongo.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

  private String firstName;

  private String lastName;

  private Date dateOfBirth;

  @Singular("address")
  private List<AddressEntity> addresses;
}
