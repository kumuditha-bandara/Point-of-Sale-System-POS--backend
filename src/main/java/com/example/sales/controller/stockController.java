package com.example.sales.controller;

import com.example.sales.model.Stock;
import com.example.sales.dto.StockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sales.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")

public class stockController {

    @Autowired
    private StockService stockService;

    // Create operation
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addStocks")
    public ResponseEntity<Stock> addStock(@RequestBody StockRequest stockRequest) {
        Stock newStock = stockService.addStock(stockRequest);
        return new ResponseEntity<>(newStock, HttpStatus.CREATED);
    }

    // Read operation
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/viewStocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    // Read operation
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/readOneStock/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable int id) throws Exception {
        Stock stock = stockService.getStockById(id);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    // Update operation
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateStock/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody StockRequest stockRequest) throws Exception {
        Stock updatedStock = stockService.updateStock(id, stockRequest);
        return new ResponseEntity<>(updatedStock, HttpStatus.OK);
    }

    // Delete operation
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/removeStock/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}