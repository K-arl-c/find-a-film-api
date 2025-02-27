package com.example.find_a_film_API.Controller;

import com.example.find_a_film_API.Models.Movie;
import com.example.find_a_film_API.Models.Review;
import com.example.find_a_film_API.Repositories.MovieRepository;
import com.example.find_a_film_API.Repositories.ReviewRepository;
import com.example.find_a_film_API.Service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Review>> getAllReviewsForMovie(@PathVariable Long movieId) {
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        if(reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<String> createReview(@PathVariable Long movieId, @RequestBody Review newReview) {
        try {
            Review createdReview = reviewService.createReview(newReview, movieId);
            return ResponseEntity.ok(String.format("Review for movie %s was added successfully", createdReview.getMovie().getTitle()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Movie with ID %d can not be found", movieId));
        }
    }

}
