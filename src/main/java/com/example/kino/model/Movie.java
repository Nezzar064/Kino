package com.example.kino.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieID;

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "movie_duration")
    private String movieDuration;

    @Column(name = "movie_age_restriction")
    private String movieAgeRestriction;

    @Column(name = "movie_category")
    @Enumerated(EnumType.STRING)
    @Getter
    private MovieCategory movieCategory;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Actor> actors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(movieID, movie.movieID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieID);
    }
}
