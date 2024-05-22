package com.example.sales.repository;

import com.example.sales.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// ItemRepository.java
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
