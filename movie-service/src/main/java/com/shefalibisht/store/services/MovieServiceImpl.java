package com.shefalibisht.store.services;

import com.shefalibisht.store.exceptions.RecordNotFoundException;
import com.shefalibisht.store.models.Movie;
import com.shefalibisht.store.repositories.MovieRepository;
import com.shefalibisht.store.repositories.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieCategoryRepository repository;
    @Autowired
    private MovieRepository movieRepo;

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }


    public Movie getMoviesById(Long id) throws RecordNotFoundException
    {
        Optional<Movie> movie = movieRepo.findById(id);
        if(movie.isPresent()) {
            return movie.get();
        } else {
            throw new RecordNotFoundException("No movie record exist for given id");
        }
    }


    public Date getCurrentDateTime() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        Date date1 = calobj.getTime();
        return  date1;
    }

//    public void deleteMovie(Long id){
//        if (movieRepo.existsById(id)){
//            // Delete user
//            movieRepo.deleteById(id);
//        }else{
//            throw new RecordNotFoundException("No store record exist for given id");
//        }
//    }

}
