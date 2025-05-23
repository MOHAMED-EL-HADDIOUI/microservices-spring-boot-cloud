package com.shefalibisht.store.services;

import com.shefalibisht.store.exceptions.RecordNotFoundException;
import com.shefalibisht.store.models.Store;
import com.shefalibisht.store.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository repository;

    public List<Store> getAllStores()
    {
        List<Store> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Store>();
        }
    }

    public Store getOneStore(Long id) throws RecordNotFoundException
    {
        Optional<Store> store = repository.findById(id);

        if(store.isPresent()) {
            return store.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public Store addStore(Store s){
        System.out.println("Inside addStore");
        return repository.save(s);
    }

}
