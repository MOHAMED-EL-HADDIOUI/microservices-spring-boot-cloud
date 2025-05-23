package com.shefalibisht.rental.services;

import com.shefalibisht.rental.exceptions.RecordNotFoundException;
import com.shefalibisht.rental.models.Inventory;
import com.shefalibisht.rental.models.MovieRental;
import com.shefalibisht.rental.models.Store;
import com.shefalibisht.rental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService{
    @Autowired
    private RentalRepository repository;

    @Autowired
    private WebClient.Builder webClient;


    public List<MovieRental> getAllRentals(){
        return repository.findAll();
    }


    public MovieRental getRentalsById(Long id) throws RecordNotFoundException
    {
        Optional<MovieRental> movie = repository.findById(id);

        if(movie.isPresent()) {
            return movie.get();
        } else {
            throw new RecordNotFoundException("No movie record exist for given id");
        }
    }

    public Inventory getInventoryService(Long id){
        Inventory inv = webClient.build().get()
                .uri("http://inventory-service/testing/"+id)
                .retrieve()
                .bodyToMono(Inventory.class).block();
        System.out.println("jj"+ inv);
        return inv;
    }


    public Inventory updateInventoryQtyService(Long id){
        Inventory inv = webClient.build().get()
                .uri("http://inventory-service/testing/update/"+id)
                .retrieve()
                .bodyToMono(Inventory.class).block();
        System.out.println("Inside updateInventoryQtyService"+ inv);
        return inv;
    }


    public MovieRental addRentals(MovieRental rental) throws RecordNotFoundException
    {
        try {
            // When a new rental is added, check quantity for the inv_id
            // quantity> 0 and is_active=1
            // Quantity --
            Inventory inv = getInventoryService(rental.getInventoryId());
            // Inventory should exist and isActive
            if(inv != null && inv.getIs_active()){
                if (inv.getQuantity() > 0){
                    // Decrease quantity by 1
                    inv.setQuantity(inv.getQuantity()-1);
                    System.out.println("id ->: \t" + inv);
                    updateInventoryQtyService(inv.getId());
                    return repository.save(rental);
                }else{
                    throw new RecordNotFoundException("Not enough quantity for given id");
                }
            }
            throw new RecordNotFoundException("No store record exist for given id");
        } catch (Exception e){
            throw new RecordNotFoundException("No store record exist for given id");
        }
    }

    public MovieRental isRentalPresent(Long id){
        Optional<MovieRental> rental = repository.findById(id);
        if (rental.isPresent()){
            return rental.get();
        }
        else{
            return null;
        }
    }

    public void removeRentals(Long id) throws RecordNotFoundException
    {
        try {
            repository.deleteById(id);
        }
        catch (Exception e){
            throw new RecordNotFoundException("No Rental record exist for given id");
        }
    }




}
