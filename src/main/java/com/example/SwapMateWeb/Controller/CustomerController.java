package com.example.SwapMateWeb.Controller;
import com.example.SwapMateWeb.Service.CustomerService;
import com.example.SwapMateWeb.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class CustomerController {
    @Autowired
    public CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @GetMapping("/getCustomerDetails")
    public Customer getCustomer(@RequestParam String email ) throws InterruptedException, ExecutionException{
        return customerService.getCustomer(email);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer() throws InterruptedException, ExecutionException{
        return customerService.getAllCustomer();
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestBody Customer customer ) throws InterruptedException, ExecutionException {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customer")
    public String updateCustomer(@RequestBody Customer customer ) throws InterruptedException, ExecutionException {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer")
    public String deleteCustomer(@RequestBody Customer customer) throws ExecutionException, InterruptedException {
        return customerService.deleteCustomer(customer);
    }
    //Test Endpoint
    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint(){
        return ResponseEntity.ok("Test Get Endpoint is Working");
    }
}
