package com.shefalibisht.rental.controllers;

import com.shefalibisht.rental.exceptions.RecordNotFoundException;
import com.shefalibisht.rental.models.Inventory;
import com.shefalibisht.rental.models.MovieRental;
import com.shefalibisht.rental.services.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path="/api/rentals")
public class RentalController {

    @Autowired
    private RentalServiceImpl movieService;

    // create-> When user add a rental, we get inventory_id
    // Find filmid and storeid for inventory_id from inventory Table(Api)
    // Fetch CustomerId
    // MovieRental(id=1, rental_date=null, customerId=null,
    // inventoryId=null, return_date=Thu Jan 01 05:30:02 IST 1970, last_update=null)

    @PostMapping({"/add", "/testing/add"})
    public ResponseEntity<MovieRental> addRental (@RequestBody MovieRental rent){
        System.out.println("inPUT is->: \t" + rent);
        MovieRental rental = movieService.addRentals(rent);
        return new ResponseEntity<MovieRental>(rental, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/testing/{id}")
    public Inventory testi(@PathVariable("id") Long id){
        Inventory inv = movieService.getInventoryService(id);
        return inv;
    }

    @DeleteMapping({"/{id}","/testing/{id}"})
    public ResponseEntity<HttpStatus> removeRental (@PathVariable("id") Long id){
        try {
            if (movieService.isRentalPresent(id) != null){
                movieService.removeRentals(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/all", "/testing/all"})
    public ResponseEntity<List<MovieRental>> getAll (){

        List<MovieRental> list = movieService.getAllRentals();
        return new ResponseEntity<List<MovieRental>>(list, new HttpHeaders(), HttpStatus.OK);
    }


}
