package com.example.find_a_film_API.Service;

import com.example.find_a_film_API.Models.Movie;
import com.example.find_a_film_API.Models.Review;
import com.example.find_a_film_API.Repositories.MovieRepository;
import com.example.find_a_film_API.Repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review newReview, Long movieId){
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Movie with id %d does not exist", movieId )));
        newReview.setMovie(movie);
        return reviewRepository.save(newReview);
    }
}
