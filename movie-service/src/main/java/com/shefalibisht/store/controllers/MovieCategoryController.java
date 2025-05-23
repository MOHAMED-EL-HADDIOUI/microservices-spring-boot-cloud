package com.shefalibisht.store.controllers;

import com.shefalibisht.store.exceptions.RecordNotFoundException;
import com.shefalibisht.store.models.Movie_Category;
import com.shefalibisht.store.services.MovieCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class MovieCategoryController {

    @Autowired
    private MovieCategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<Movie_Category>> getAllCategories() throws RecordNotFoundException  {
        List<Movie_Category> list = categoryService.getAllCategory();
        return new ResponseEntity<List<Movie_Category>>(list, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
    @GetMapping({"/{id}", "/testing/{id}"})
    public ResponseEntity<Movie_Category> getOneCategory(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Movie_Category entity = categoryService.getOneCategory(id);
        return new ResponseEntity<Movie_Category>(entity, new HttpHeaders(), HttpStatus.OK);
    }

}
