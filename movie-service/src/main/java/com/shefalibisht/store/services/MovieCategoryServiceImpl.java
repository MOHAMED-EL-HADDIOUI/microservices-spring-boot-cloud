package com.shefalibisht.store.services;

import com.shefalibisht.store.exceptions.RecordNotFoundException;
import com.shefalibisht.store.models.Movie_Category;
import com.shefalibisht.store.repositories.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieCategoryServiceImpl implements MovieCategoryService {

    @Autowired
    private MovieCategoryRepository repository;


    public List<Movie_Category> getAllCategory()
    {
        List<Movie_Category> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Movie_Category>();
        }
    }

    public Movie_Category getOneCategory(Long id) throws RecordNotFoundException
    {
        Optional<Movie_Category> store = repository.findById(id);

        if(store.isPresent()) {
            return store.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }



}
