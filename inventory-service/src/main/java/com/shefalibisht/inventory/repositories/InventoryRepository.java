package com.shefalibisht.inventory.repositories;

import com.shefalibisht.inventory.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
