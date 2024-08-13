package org.example.seatreservationsystem.service;


import org.example.seatreservationsystem.entities.Customer;
import org.example.seatreservationsystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public void addCustomer(Customer customer) {
        if (repository.existsBySeatno(customer.getSeatno())) {
            throw new IllegalArgumentException("Seat already reserved");
        }
        repository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public void updateCustomer(Customer customer) {
        repository.save(customer);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
