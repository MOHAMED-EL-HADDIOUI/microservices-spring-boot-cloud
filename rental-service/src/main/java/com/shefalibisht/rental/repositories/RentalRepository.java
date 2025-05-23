package com.shefalibisht.rental.repositories;


import com.shefalibisht.rental.models.MovieRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Repository
public interface RentalRepository extends JpaRepository<MovieRental, Long> {

}
