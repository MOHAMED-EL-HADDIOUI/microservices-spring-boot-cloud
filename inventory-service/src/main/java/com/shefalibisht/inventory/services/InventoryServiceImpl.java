package com.shefalibisht.inventory.services;

import com.shefalibisht.inventory.exceptions.RecordNotFoundException;
import com.shefalibisht.inventory.models.*;
import com.shefalibisht.inventory.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl {

    @Autowired
    private InventoryRepository invRepo;

    @Autowired
    private WebClient.Builder webClient;

    public List<Inventory> getAllInventory(){
        return invRepo.findAll();
    }


    public Inventory updateQuantity(Long id){
        System.out.println("Inside addStore");
        Optional<Inventory> movie = invRepo.findById(id);
        if(movie.isPresent() && movie.get().getIs_active()) {
            movie.get().setQuantity(movie.get().getQuantity()-1);
            if (movie.get().getQuantity() == 0){
                System.out.println("\nQuantity is : "+ movie.get().getQuantity());
                movie.get().setIs_active(false);
            }
            return invRepo.save(movie.get());
        } else {
            throw new RecordNotFoundException("No Active Inventory exist for given id");
        }
    }

    public Inventory getInventoryById(Long id) throws RecordNotFoundException
    {
        Optional<Inventory> movie = invRepo.findById(id);

        if(movie.isPresent()) {
            return movie.get();
        } else {
            throw new RecordNotFoundException("No Inv record exist for given id");
        }
    }

    public InventoryOutput  getOneInventoryDetails(Long id){
        System.out.println("ins");
        Optional<Inventory> inventory = invRepo.findById(id);
        if(inventory.isPresent()) {
            System.out.println(inventory);
            InventoryOutput invOutput = new InventoryOutput();
            invOutput.setId(inventory.get().getId());
            invOutput.setLast_update(inventory.get().getLast_update());
            invOutput.setMovie(movieApi(inventory.get().getMovieId()));
            invOutput.setStore(storeApi(inventory.get().getStoreId()));
            invOutput.setIs_active(inventory.get().getIs_active());
            invOutput.setQuantity(inventory.get().getQuantity());
            System.out.println(invOutput);
            return invOutput;
        } else {
            throw new RecordNotFoundException("No Inv record exist for given id");
        }
    }

    public List<InventoryOutput>  getAllInventoryDetails(){
        System.out.println("\n in fn");
        try{
            List<Inventory> inventory = invRepo.findAll();
            return inventory.stream().map(inv -> {
                System.out.println("\n my9822 in fn"+ inv.getId());
                InventoryOutput invOutput = new InventoryOutput();
                invOutput.setId(inv.getId());
                invOutput.setLast_update(inv.getLast_update());
                invOutput.setMovie(movieApi(inv.getMovieId()));
                invOutput.setStore(storeApi(inv.getStoreId()));
                invOutput.setIs_active(inv.getIs_active());
                invOutput.setQuantity(inv.getQuantity());
                return invOutput;
            }).collect(Collectors.toList());
        } catch (Exception e){
            throw new RecordNotFoundException("No Inv record exist for given id");
        }
    }

    public Inventory updateInventory(Inventory inv){
        System.out.println("updateInventory-> "+ inv);
        if(invRepo.existsById(inv.getId())){
            return invRepo.save(inv);
        }
        return null;
    }


    public Movie movieApi(Long id){
        System.out.println("\n  Inside movieApi");
//        .uri("http://localhost:8080/movie-service/api/movies/"+id)
        Movie response =  webClient.build().get()
                .uri("http://movie-service/testing/"+id)
                .retrieve().bodyToMono(Movie.class).block();
        System.out.println("\n movieApi-Response "+ response);
        return response;
    }

    public Store storeApi(Long id){
        System.out.println("\n  Inside StoreApi");
        Store response =  webClient.build().get()
                .uri("http://store-service/testing/"+id)
                .retrieve().bodyToMono(Store.class).block();
        System.out.println("\n storeApi-Response "+ response);
        return response;
    }


}