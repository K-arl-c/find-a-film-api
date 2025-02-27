package com.example.find_a_film_API.Controller;

import com.example.find_a_film_API.Models.Movie;
import com.example.find_a_film_API.Models.Review;
import com.example.find_a_film_API.Repositories.MovieRepository;
import com.example.find_a_film_API.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @PostMapping("/populate")
    public String addTestReviews(Movie movie) {
        Long movieId = movie.getMovieId();
        Optional<Movie> film = movieRepository.findById(movieId);
        Review sevenReview = new Review("Seven holds it’s place as one of the pinnacle films of the neo‑noir, psychological thriller and serial killer genres 25 years on through it’s dark production design and solid direction from Fincher", 10, "sdraycott", film);
        reviewRepository.save(sevenReview);
        return String.format("Your review for the movie %s was added to the database", movie.getTitle());
    }


}
