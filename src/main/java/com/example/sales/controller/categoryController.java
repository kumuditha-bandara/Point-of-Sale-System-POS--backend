package com.example.sales.controller;

import com.example.sales.model.Category;
import com.example.sales.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class categoryController {

    @Autowired
    private categoryService categoryService;

    // Create operation
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addCategory")
    public ResponseEntity<Category> addItemCategory(@RequestBody Category category) {
        Category newCategory = categoryService.addItemCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    // Read operation
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/viewCategory")
    public ResponseEntity<List<Category>> getAllItemCategories() {
        List<Category> categories = categoryService.getAllItemCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Read operation
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/viewOneCategory/{id}")
    public ResponseEntity<Category> getItemCategoryById(@PathVariable int id) throws Exception {
        Category category = categoryService.getItemCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Update operation
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Category> updateItemCategory(@PathVariable int id, @RequestBody Category category) throws Exception {
        Category updatedCategory = categoryService.updateItemCategory(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Delete operation
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Void> deleteItemCategory(@PathVariable int id) {
        categoryService.deleteItemCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}