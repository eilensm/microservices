package de.itemis.bonn.rating.persistence.mongo;

import de.itemis.bonn.rating.Address;
import de.itemis.bonn.rating.persistence.mongo.model.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AddressEntityMapper {

  @Mappings({
      @Mapping(target = "streetAndHouseNo", expression = "java(address.getStreet() + \" \" + address.getHouseNo())" )
  })
  AddressEntity modelToEntity(Address address);

  @Mappings({
      @Mapping(source = "streetAndHouseNo", target = "street")
  })
  Address entityToModel(AddressEntity addressEntity);
}
