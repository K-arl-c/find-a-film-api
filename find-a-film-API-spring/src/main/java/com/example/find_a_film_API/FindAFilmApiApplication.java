package com.example.find_a_film_API;

import jakarta.persistence.Access;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.AccessType;


@SpringBootApplication
public class FindAFilmApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindAFilmApiApplication.class, args);
	}
}
