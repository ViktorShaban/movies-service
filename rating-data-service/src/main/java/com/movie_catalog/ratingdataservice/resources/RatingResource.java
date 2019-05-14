package com.movie_catalog.ratingdataservice.resources;

import com.movie_catalog.ratingdataservice.model.Rating;
import com.movie_catalog.ratingdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRatingData(@PathVariable String movieId) {
        return new Rating(movieId, "15");
    }

    @RequestMapping("users/{userId}")
    public UserRating getUsersRatingsData(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", "3"),
                new Rating("5678", "4")
                );
        return new UserRating(ratings);
    }
}
