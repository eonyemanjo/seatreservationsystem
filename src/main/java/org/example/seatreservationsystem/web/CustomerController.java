package org.example.seatreservationsystem.web;



import org.example.seatreservationsystem.entities.Customer;
import org.example.seatreservationsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Customer> customers = service.getAllCustomers();
        Map<String, String> seats = new HashMap<>();

        // Initialize seat map with all seats
        String[] seatCodes = {"1A", "1B", "1C", "1D", "1E", "2A", "2B", "2C", "2D", "2E", "3A", "3B", "3C", "3D", "3E", "4A", "4B", "4C", "4D", "4E"};
        for (String seatCode : seatCodes) {
            seats.put(seatCode, seatCode); // Initially set seat code as value
        }

        // Replace seat code with customer name if reserved
        for (Customer customer : customers) {
            seats.put(customer.getSeatno(), customer.getName());
        }

        model.addAttribute("seats", seats);
        model.addAttribute("remainingSeats", 20 - customers.size());
        model.addAttribute("customer", new Customer());
        model.addAttribute("listCustomers", customers);
        return "index";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        service.addCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Customer customer = service.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") long id) {
        service.deleteCustomer(id);
        return "redirect:/";
    }
}
