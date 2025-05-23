package com.shefalibisht.store.controllers;

import com.shefalibisht.store.exceptions.RecordNotFoundException;
import com.shefalibisht.store.models.Movie;
import com.shefalibisht.store.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies (){
        List<Movie> list = movieService.getAllMovies();
        return new ResponseEntity<List<Movie>>(list, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
    @RequestMapping({"/{id}", "/testing/{id}"})
    @GetMapping
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Movie entity = movieService.getMoviesById(id);
        return new ResponseEntity<Movie>(entity, new HttpHeaders(), HttpStatus.OK);
    }


}
