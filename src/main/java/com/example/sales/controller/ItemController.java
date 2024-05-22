package com.example.sales.controller;

import com.example.sales.dto.ItemRequest;
import com.example.sales.model.Item;
import com.example.sales.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create operation
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createItem")
    public ResponseEntity<Item> addItem(@RequestBody ItemRequest itemRequest) throws Exception {
        Item newItem = itemService.addItem(itemRequest);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // Read operation
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/retrieveItem")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Read operation
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/retrieveOneRecord/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) throws Exception {
        Item item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // Update operation
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateRecord/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody ItemRequest itemRequest) throws Exception {
        Item updatedItem = itemService.updateItem(id, itemRequest);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // Delete operation
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
