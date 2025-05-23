package com.shefalibisht.store.controllers;

import com.shefalibisht.store.exceptions.RecordNotFoundException;
import com.shefalibisht.store.models.Store;
import com.shefalibisht.store.services.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private Environment env;

    @Autowired
    private StoreServiceImpl storeService;

    @GetMapping("/port-info")
    public String getInstancePort() {
        return "Port: "+ env.getProperty("local.server.port");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Store>> getAllStores() throws RecordNotFoundException  {
        List<Store> list = storeService.getAllStores();
        return new ResponseEntity<List<Store>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/testing/{id}"})
    public ResponseEntity<Store> getOneStores(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Store entity = storeService.getOneStore(id);
        return new ResponseEntity<Store>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping({"/add", "/testing/add"})
    public ResponseEntity<Store> addNewStore(@RequestBody Store store){
        Store entity = storeService.addStore(store);
        return new ResponseEntity<Store>(entity, new HttpHeaders(), HttpStatus.CREATED);
    }

}
