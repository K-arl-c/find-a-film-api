package com.example.find_a_film_API.Controller;

import com.example.find_a_film_API.Models.Movie;
import com.example.find_a_film_API.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Optional<Movie> getMovieById(@PathVariable(required = true) Long movieId) {
        return movieRepository.findById(movieId);
    }

    @PostMapping("/")
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

        Movie seven = new Movie("Seven", "Thriller", "Detectives Somerset and Mills are paired up to solve murders. Together they attempt to find a killer who is inspired by the seven deadly sins.", 1995, 7, "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTGOnL9G5Bx6q6vw2ToNKpZLCsaKB_ZA1ZsmJLXDc-SfX04RUQXjiFEccEaLoxxWRIkL4Ct",  "sdraycott");
        testMovies.add(seven);

        Movie jurassicPark = new Movie("Jurassic Park", "Science-fiction", "An industrialist invites some experts to visit his theme park of cloned dinosaurs. After a power failure, the creatures run loose, putting everyone's lives, including his grandchildren's, in danger.", 1993, 8, "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQ1JuD1fDYYs7-IqHydpi304Jr3eZsBW9i58o6yTa7d3tGzKFJvcFGwatAtxLIhTZaWJ8tY", "sdraycott");
        testMovies.add(jurassicPark);


        Movie jaws = new Movie("Jaws", "Thriller", "When a giant white shark fatally attacks swimmers on the shores of Amity Island, Sheriff Martin Brody teams up with a marine biologist and a local fisherman to hunt down the creature.", 1975, 9, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTM80rW1WI9XwnKfJuLCf8YlLwbQSvavcGPTbNNFY0MfZWPrOg8LMTce7Ml6zAPxIqbPTff", "sdraycott");
        testMovies.add(jaws);

        movieRepository.saveAll(testMovies);

        return "Test movies have been added to the database";
    }

}
