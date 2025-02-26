package com.example.find_a_film_API.Repositories;


import com.example.find_a_film_API.Models.MovieItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieItem, Long> {

}
