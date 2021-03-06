package com.example.kino.controller;
//TODO: Payload required for create movie enum switch

import com.example.kino.model.Movie;
import com.example.kino.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:63342", "http://localhost:5000"})
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping(value = "/createMovie")
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) throws URISyntaxException {
        Movie result = null;
        result = movieService.saveMovie(movie);
        return ResponseEntity.created(new URI("/getMovie/" + result.getMovieID())).body((result));
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie tmpMovie = movieService.updateMovie(movie, id);
        return ResponseEntity.ok().body(tmpMovie);
    }

    @DeleteMapping("/movie/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showAllMovies")
    public ResponseEntity<List<Movie>> showAllMovies(){
        List<Movie> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

}
