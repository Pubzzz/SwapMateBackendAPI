package com.example.SwapMateWeb.Controller;
import com.example.SwapMateWeb.Service.OrderService;
import com.example.SwapMateWeb.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")

@RestController
public class OrderController {
    @Autowired
    public OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @GetMapping("/getOrderDetails")
    public Order getOrder(@RequestParam String document_id ) throws InterruptedException, ExecutionException{
        return orderService.getOrder(document_id);
    }

    @GetMapping("/getAllOrderDetails")
    public List<Order> getAllOrder() throws InterruptedException, ExecutionException{
        return orderService.getAllOrder();
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Order order ) throws InterruptedException, ExecutionException {
        return orderService.createOrder(order);
    }

    @PutMapping("/updateOrder")
    public String updateOrder(@RequestBody Order order  ) throws InterruptedException, ExecutionException {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestParam String document_id){
        return orderService.deleteOrder(document_id);
    }
}
