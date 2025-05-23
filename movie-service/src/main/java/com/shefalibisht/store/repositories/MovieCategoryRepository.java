package com.shefalibisht.store.repositories;


import com.shefalibisht.store.models.Movie_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


// Spring container will create all of the REST services and CRUD operations for this entity,
// which we can customize as per our needs
@CrossOrigin("http://localhost:4200")
@Repository
public interface MovieCategoryRepository extends JpaRepository<Movie_Category, Long> {

}
