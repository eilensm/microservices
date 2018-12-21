package de.itemis.bonn.rating.persistence.mongo;

import de.itemis.bonn.rating.Rating;
import de.itemis.bonn.rating.persistence.mongo.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingEntityMapper {

  RatingEntityMapper INSTANCE = Mappers.getMapper(RatingEntityMapper.class);

  Rating fromEntityToModel(RatingEntity ratingEntity);

  RatingEntity modelToEntity(Rating rating);
}
