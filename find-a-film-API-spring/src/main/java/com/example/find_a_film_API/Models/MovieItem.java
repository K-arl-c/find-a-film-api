package com.example.find_a_film_API.Models;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "movies")
public class MovieItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String genre;
    private String description;
    private Short releaseYear;
    private byte rating;
    private String uploadedBy;
    private String imageURL;

    //No param constructor:
    public MovieItem() {
    }

    //Constructor - doesn't take ID as this is auto-generated

    public MovieItem(String title, String genre, String description, Short releaseYear, byte rating, String uploadedBy, String imageURL) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.uploadedBy = uploadedBy;
        this.imageURL = imageURL;
    }

    //Getters and Setters
    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
