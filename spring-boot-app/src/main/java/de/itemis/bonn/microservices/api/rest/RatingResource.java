package de.itemis.bonn.microservices.api.rest;

import de.itemis.bonn.rating.Rating;
import de.itemis.bonn.rating.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/ratings")
public class RatingResource {

  private final RatingService ratingService;

  public RatingResource(final RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @RequestMapping(method = POST)
  @ResponseBody
  public ResponseEntity<Rating> createRating(@RequestBody final Integer value) {
    return new ResponseEntity<>(ratingService.createRating(value), HttpStatus.CREATED);
  }

  @RequestMapping(path = "/{id}", method = PUT)
  public Rating createRating(@PathVariable final String id, @RequestBody final Rating rating) {
    if (!Objects.equals(id, rating.getId())) {
      throw new RuntimeException(id + " != " + rating.getId());
    }
    return ratingService.rate(rating);
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
