package com.shefalibisht.store.repositories;

import com.shefalibisht.store.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
