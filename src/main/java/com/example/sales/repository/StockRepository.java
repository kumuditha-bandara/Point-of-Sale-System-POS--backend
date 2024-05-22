package com.example.sales.repository;

import com.example.sales.model.CartItem;
import com.example.sales.model.Invoice;
import com.example.sales.model.Item;
import com.example.sales.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByItem(Item item);
}


