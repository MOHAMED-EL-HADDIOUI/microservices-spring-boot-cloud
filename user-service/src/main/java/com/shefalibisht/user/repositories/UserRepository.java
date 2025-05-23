package com.shefalibisht.user.repositories;

import com.shefalibisht.user.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {

}
