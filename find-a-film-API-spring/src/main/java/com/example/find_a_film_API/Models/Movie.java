package com.example.find_a_film_API.Models;
import jakarta.persistence.*;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String genre;
    private String description;
    private int releaseYear;
    private int rating;
    private String imageURL;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    //No param constructor:
    public Movie() {
    }


    //Constructor

    public Movie(String title, String genre, String description, int releaseYear, int rating, String imageURL, User user) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.imageURL = imageURL;
        this.user = user;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
