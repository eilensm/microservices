package de.itemis.bonn.rating.persistence.mongo;

import de.itemis.bonn.rating.Person;
import de.itemis.bonn.rating.persistence.mongo.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressEntityMapper.class)
public interface PersonMapper {

  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  PersonEntity modelToEntity(Person person);

  Person entityToModel(PersonEntity personEntity);
}
