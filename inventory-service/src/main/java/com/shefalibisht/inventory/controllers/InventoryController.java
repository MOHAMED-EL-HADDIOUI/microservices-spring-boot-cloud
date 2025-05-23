package com.shefalibisht.inventory.controllers;


import com.shefalibisht.inventory.exceptions.RecordNotFoundException;
import com.shefalibisht.inventory.models.Inventory;
import com.shefalibisht.inventory.models.InventoryOutput;
import com.shefalibisht.inventory.models.Movie;
import com.shefalibisht.inventory.models.Store;
import com.shefalibisht.inventory.services.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class InventoryController {

    @Autowired
    private InventoryServiceImpl movieService;

    @GetMapping({"/all", "/testing/all"})
    public List<InventoryOutput> getAllInventoryDetails(@RequestParam(required = false) String category) {
        if (category == null){
            return movieService.getAllInventoryDetails();
        }else{
            List<InventoryOutput> res = movieService.getAllInventoryDetails();
            Predicate<InventoryOutput> byCategory = x -> x.getMovie().getCategory().getName().equalsIgnoreCase(category);

            List<InventoryOutput> result = res.stream().filter(byCategory)
                    .collect(Collectors.toList());
            return result;
        }
    }



    @GetMapping("/testing/update/{id}")
    public Inventory updateQuantity(@PathVariable("id") Long id){
        return movieService.updateQuantity(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryOutput> getOneInventoryDetails(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        InventoryOutput entity = movieService.getOneInventoryDetails(id);
        return new ResponseEntity<InventoryOutput>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/testing/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Inventory entity = movieService.getInventoryById(id);
        return new ResponseEntity<Inventory>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping({"/{id}", "/testing/{id}"})
    public ResponseEntity<Inventory> putRentalsById(@PathVariable("id") Long id, @RequestBody Inventory inv)
            throws RecordNotFoundException {
        Inventory entity = movieService.updateInventory(inv);
        return new ResponseEntity<Inventory>(entity, new HttpHeaders(), HttpStatus.OK);
    }

}
