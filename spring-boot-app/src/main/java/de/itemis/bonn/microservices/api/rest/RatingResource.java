package de.itemis.bonn.microservices.api.rest;

import de.itemis.bonn.microservices.api.rest.model.CreateRatingRequestDto;
import de.itemis.bonn.rating.Rating;
import de.itemis.bonn.rating.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/ratings")
public class RatingResource {

  private final RatingService ratingService;

  public RatingResource(final RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @RequestMapping(method = POST)
  @ResponseBody
  public ResponseEntity<Rating> createRating(@RequestBody final CreateRatingRequestDto createRatingRequestDto) {
    final Rating rating = ratingService.createRating(
        createRatingRequestDto.getCustomerName(),
        createRatingRequestDto.getRatingPd(),
        createRatingRequestDto.getRatingClass());
    return new ResponseEntity<>(rating, HttpStatus.CREATED);
  }

  @RequestMapping(path = "/{id}", method = GET)
  public @ResponseBody
  Rating getRating(@PathVariable final String id) {
    return ratingService.getRating(id);
  }

  @RequestMapping(method = GET)
  public @ResponseBody
  List<Rating> getRatings() {
    return ratingService.getRatings();
  }
}
