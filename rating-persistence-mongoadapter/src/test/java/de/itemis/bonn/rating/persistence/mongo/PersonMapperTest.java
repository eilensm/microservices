package de.itemis.bonn.rating.persistence.mongo;

import de.itemis.bonn.rating.Address;
import de.itemis.bonn.rating.Person;
import de.itemis.bonn.rating.persistence.mongo.model.AddressEntity;
import de.itemis.bonn.rating.persistence.mongo.model.PersonEntity;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

public class PersonMapperTest {

  @Test
  public void modelToEntity() {
    final Person person = Person.builder()
        .address(Address.builder()
            .street("Main street")
            .postalCode("51105")
            .houseNo("18")
            .build())
        .build();
    final PersonEntity actual = PersonMapper.INSTANCE.modelToEntity(person);
    assertThat(actual.getAddresses())
        .extracting(AddressEntity::getStreetAndHouseNo, AddressEntity::getPostalCode)
        .containsExactly(tuple("Main street 18", 51105));
  }
}
