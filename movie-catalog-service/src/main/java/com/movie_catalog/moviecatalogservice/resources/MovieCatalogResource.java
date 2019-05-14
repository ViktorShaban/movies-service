package com.movie_catalog.moviecatalogservice.resources;

import com.movie_catalog.moviecatalogservice.model.CatalogItem;
import com.movie_catalog.moviecatalogservice.model.Movie;
import com.movie_catalog.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @RequestMapping("/items/{userId}")
    public List<CatalogItem> getCatalogItemList(@PathVariable String userId) {
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratings/users/1", UserRating.class);

        return ofNullable(userRating).map(UserRating::getUserRating)
                .orElse(emptyList())
                .stream()
                .map(r-> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/info/" + r.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getMovieName(), "Good movie", r.getRating());
                })
                .collect(toList());
    }

}

//                    Movie movie = webClientBuilder.build()
//                            .get()
//                            .uri("http://localhost:8087/movies/info/" + r.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();
