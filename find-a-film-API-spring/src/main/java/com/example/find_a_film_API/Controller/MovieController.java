package com.example.find_a_film_API.Controller;

import com.example.find_a_film_API.Models.Movie;
import com.example.find_a_film_API.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(required = true) Long movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);

        if (movieOpt.isPresent()) {
            return ResponseEntity.ok(movieOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{movieId}/average-rating")
    public ResponseEntity<Integer> getMovieAverageRating(@PathVariable Long movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);

        if (movieOpt.isPresent()) {
            int averageRating = movieRepository.getAverageRatingForMovie(movieId); // Custom query for average rating
            return ResponseEntity.ok(averageRating);  // Return the average rating as a double
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if movie is not found
        }
    }

    @GetMapping("/sort")
    public Iterable<Movie> getSortedMovies(
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        if(sortBy.equalsIgnoreCase("releaseYear")) {
            return movieRepository.findAll(Sort.by(sortDirection, "releaseYear"));
        } else if("rating".equalsIgnoreCase(sortBy)) {
            return movieRepository.findAllMoviesSortedByAverageRating(Sort.by(sortDirection, "rating"));
        }
        else {
            return movieRepository.findAll(Sort.by(sortDirection, "title"));
        }
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    @DeleteMapping("/{movieId}")
    public String deleteById(@PathVariable(required = true) Long movieId){
        Movie target = movieRepository.findById(movieId).get();
        movieRepository.delete(target);
        return String.format("The movie '%s' with id: %d a has been successfully deleted", target.getTitle(), movieId);
    }

    @PostMapping("/populate")
    public String addTestMovies() {
        if(movieRepository.findByTitle("Jaws") != null) {
            return "Test movies have already been added to the database";
        }
        List<Movie> testMovies = new ArrayList<>();

        Movie seven = new Movie("Seven", "Thriller", "Detectives Somerset and Mills are paired up to solve murders. Together they attempt to find a killer who is inspired by the seven deadly sins.", 1995, "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTGOnL9G5Bx6q6vw2ToNKpZLCsaKB_ZA1ZsmJLXDc-SfX04RUQXjiFEccEaLoxxWRIkL4Ct",  "sdraycott");
        testMovies.add(seven);

        Movie jurassicPark = new Movie("Jurassic Park", "Science-fiction", "An industrialist invites some experts to visit his theme park of cloned dinosaurs. After a power failure, the creatures run loose, putting everyone's lives, including his grandchildren's, in danger.", 1993, "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQ1JuD1fDYYs7-IqHydpi304Jr3eZsBW9i58o6yTa7d3tGzKFJvcFGwatAtxLIhTZaWJ8tY", "sdraycott");
        testMovies.add(jurassicPark);


        Movie jaws = new Movie("Jaws", "Thriller", "When a giant white shark fatally attacks swimmers on the shores of Amity Island, Sheriff Martin Brody teams up with a marine biologist and a local fisherman to hunt down the creature.", 1975, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTM80rW1WI9XwnKfJuLCf8YlLwbQSvavcGPTbNNFY0MfZWPrOg8LMTce7Ml6zAPxIqbPTff", "sdraycott");
        testMovies.add(jaws);

        movieRepository.saveAll(testMovies);

        return "Test movies have been added to the database";
    }

}
