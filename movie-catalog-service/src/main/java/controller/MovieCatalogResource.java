package controller;


import model.CatalogItem;
import model.Movie;
import model.Rating;
import model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){


       // hard coded
        /*List<Rating> ratingList = Arrays.asList(
                new Rating("1234",4),
                new Rating("6789",3)
        );*/

        //using rest template
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/"+userId, UserRating.class);

        return userRating.getRatingList().stream().map(rating -> {

            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);
            /*Movie movie=webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/

            return new CatalogItem(movie.getMovieName(),"test",rating.getRating());
        }).collect(Collectors.toList());

       /* return Collections.singletonList(
                new CatalogItem("kali","test",4)
        );*/


    }


}
