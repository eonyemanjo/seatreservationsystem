package org.example.seatreservationsystem.repositories;



import org.example.seatreservationsystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsBySeatno(String seatno);
}
