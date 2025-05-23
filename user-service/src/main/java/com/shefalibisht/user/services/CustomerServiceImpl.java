package com.shefalibisht.user.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.shefalibisht.user.exceptions.RecordNotFoundException;
import com.shefalibisht.user.models.Customer;
import com.shefalibisht.user.models.CustomerInput;
import com.shefalibisht.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private UserRepository repository;

//    public List<User> getAllUsers(){
//        return repository.findAll();
//    }

    public List<Customer> getAllUsers()
    {
        List<Customer> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Customer>();
        }
    }

    public Customer getUsersById(Long id) throws RecordNotFoundException
    {
        Optional<Customer> employee = repository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public Customer CopyDtoToEntity(CustomerInput userDto){
        //System.out.println("id userDto is->: \t" + userDto.getId());
        Customer user = new Customer();
        user.setId(userDto.getId());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setEmail(userDto.getEmail());
        user.setAddress_id(userDto.getAddress_id());
        user.setActive(userDto.getActive());
        user.setCreate_date(getCurrentDateTime());
      //  user.setCreate_date(getCurrentDateTime());
        return user;
    }

    public Date getCurrentDateTime() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
       // String dff=  calobj.getTime();
        Date date1 = calobj.getTime();
        return  date1;
    }

    public Customer createUsers(CustomerInput userDto){
        try {
            System.out.println("id is->: \t" + userDto.getId());
            Customer user = CopyDtoToEntity(userDto);
            System.out.println("UserDemo->: \t" + user);
            return repository.save(user);
        }
        catch (Exception e){
            throw new RecordNotFoundException("No store record exist for given id");
        }

    }

    public boolean existsUser(Long id){
        System.out.println(repository.existsById(id));
        return repository.existsById(id);
    }

    public Customer updateUser(Long id, CustomerInput userDto){
        try{
            Customer user = CopyDtoToEntity(userDto);
            user.setId(id);
            System.out.println("\n\n");
            System.out.println(user);
            return repository.save(user);
        }catch (Exception e){
            throw new RecordNotFoundException("No store record exist for given id");
        }

    }

    public void deleteUser(Long id){
        if (repository.existsById(id)){
            // Delete user
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No store record exist for given id");
        }

    }

}
