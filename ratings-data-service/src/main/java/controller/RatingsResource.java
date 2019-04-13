package controller;

import models.Rating;
import models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRatingInfo(@PathVariable("movieId") String movieId){
        return new Rating("movieId",4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getRating(@PathVariable("userId") String userId){

        UserRating userRating = new UserRating();

        List<Rating> ratingList=Arrays.asList(
                new Rating("1234",4),
                new Rating("4567",3)

        );

        userRating.setRatingList(ratingList);
        return userRating;

    }
}
