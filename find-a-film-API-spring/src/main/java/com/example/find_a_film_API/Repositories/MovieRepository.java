package com.example.find_a_film_API.Repositories;


import com.example.find_a_film_API.Models.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);


    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.movie.id = :movieId")
    int getAverageRatingForMovie(@Param("movieId") Long movieId);

    @Query("SELECT m, AVG(r.rating) FROM Movie m LEFT JOIN m.reviews r GROUP BY m.id ORDER BY AVG(r.rating) ASC")
    List<Movie> findAllMoviesSortedByAverageRating(Sort sort);



}
