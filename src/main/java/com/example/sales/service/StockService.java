package com.example.sales.service;

import com.example.sales.dto.StockRequest;
import com.example.sales.model.Item;
import com.example.sales.model.Stock;
import com.example.sales.repository.ItemRepository;
import com.example.sales.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ItemRepository itemRepository;

    // Create operation
    public Stock addStock(StockRequest stockRequest) {

        // Ensure the item exists
        Item item = itemRepository.findById(stockRequest.getItem_id())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Create new stock with the item
        Stock stocks = new Stock();
        stocks.setItem(item);
        stocks.setQuantity(stockRequest.getQuantity());

        // Save the stock
        return stockRepository.save(stocks);
    }

    // Read operation
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Read operation
    public Stock getStockById(int id) throws Exception {
        return stockRepository.findById(id)
                .orElseThrow(() -> new Exception("Item not found with id: " + id));
    }

    // Update operation
    public Stock updateStock(int id, StockRequest stockRequest) throws Exception {
        Stock existingStock = getStockById(id);
        Item item = itemRepository.findById(stockRequest.getItem_id())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existingStock.setItem(item); // Set the fetched Item object
        existingStock.setQuantity(stockRequest.getQuantity());
        existingStock.setQuantity(stockRequest.getQuantity());
        // Update other fields as needed
        return stockRepository.save(existingStock);
    }

    // Delete operation
    public void deleteStock(int id) {
        stockRepository.deleteById(id);
    }
}
