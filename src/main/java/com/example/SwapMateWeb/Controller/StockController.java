package com.example.SwapMateWeb.Controller;

import com.example.SwapMateWeb.Model.Image;
import com.example.SwapMateWeb.Model.Stock;
import com.example.SwapMateWeb.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class StockController {
    @Autowired
    public StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }


    @GetMapping("/stocks/getStockDetails")
    public Stock getStock(@RequestParam String document_id ) throws InterruptedException, ExecutionException {
        return stockService.getStock(document_id);
    }

    @GetMapping("/stocks")
    public List<Stock> getAllStock() throws InterruptedException, ExecutionException{
        return stockService.getAllStock();
    }

    @PostMapping("/stocks")
    public String createStock(@RequestBody Stock stock ) throws InterruptedException, ExecutionException {
        return stockService.createStock(stock);
    }

    @PutMapping("/stocks/updateStock")
    public String updateStock(@RequestBody Stock stock  ) throws InterruptedException, ExecutionException {
        return stockService.updateStock(stock);
    }

    @DeleteMapping("/stocks")
    public String deleteStock(@RequestParam String document_id){
        return stockService.deleteStock(document_id);
    }

    @PatchMapping("/stocks")
    public String updateImages(@RequestParam String document_id,@RequestBody Image image) throws ExecutionException, InterruptedException {
        return stockService.updateImages(document_id,image);
    }

}
