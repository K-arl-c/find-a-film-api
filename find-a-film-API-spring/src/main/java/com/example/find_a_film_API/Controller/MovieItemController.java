package com.example.find_a_film_API.Controller;


import com.example.find_a_film_API.Models.MovieItem;
import com.example.find_a_film_API.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieItemController {

    private MovieRepository movieRepository;

    @Autowired
    public MovieItemController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:5173/")
    public List<MovieItem> getAllMovies(){

        return movieRepository.findAll();
    }

    @GetMapping("/{movieId}")
    @CrossOrigin(origins = "http://localhost:5173/")
    public Optional<MovieItem> getMovieById(@PathVariable(required = true) Long movieId) {

        return movieRepository.findById(movieId);
    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:5173/")
    public MovieItem addMovie(@RequestBody MovieItem movieItem) {
        movieRepository.save(movieItem);
        return movieItem;
    }

    @DeleteMapping("/{movieId}")
    @CrossOrigin(origins = "http://localhost:5173/")
    public String deleteById(@PathVariable(required = true) Long movieId){
        MovieItem target = movieRepository.findById(movieId).get();
        movieRepository.delete(target);
        return String.format("The movie '%s' with id: %d a has been successfully deleted", target.getTitle(), movieId);
    }


}
