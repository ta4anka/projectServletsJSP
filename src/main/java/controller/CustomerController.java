package controller;

import model.Customer;
import repository.CustomerRepository;
import repository.hibernate.HibernateCustomerRepositoryImpl;


import java.util.List;

public class CustomerController{
    CustomerRepository customerRepository = new HibernateCustomerRepositoryImpl();

    public void save(Customer customer){
        if(customer == null){
            throw new IllegalArgumentException();
        }else {
            customerRepository.save(customer);
        }

    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void update(Customer customer) {
        if(customer == null){
            throw  new IllegalArgumentException();
        }else customerRepository.update(customer);

    }

    public void delete(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            customerRepository.delete(id);
        }

    }


    public Customer getById(Integer id) {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return customerRepository.getById(id);
    }
}
