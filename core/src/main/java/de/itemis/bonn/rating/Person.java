package de.itemis.bonn.rating;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private String firstName;

  private String lastName;

  private Date dateOfBirth;

  @Singular("address")
  private List<Address> addresses;
}
