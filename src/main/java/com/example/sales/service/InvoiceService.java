package com.example.sales.service;

import com.example.sales.model.Invoice;
import com.example.sales.model.InvoiceItem;
import com.example.sales.model.Item;
import com.example.sales.model.Stock;
import com.example.sales.repository.ItemRepository;
import com.example.sales.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class InvoiceService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    public Invoice generateInvoice(List<InvoiceItem> invoiceItems) {
        Logger logger = Logger.getLogger("CheckoutLogger");

        double totalAmount = 0;
        List<InvoiceItem> persistedInvoiceItems = new ArrayList<>();

        for (InvoiceItem invoiceItem : invoiceItems) {
            Item item = itemRepository.findById(invoiceItem.getItem().getId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            Stock stock = stockRepository.findByItem(item);
            if (stock.getQuantity() < invoiceItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for item: " + item.getName());
            }

            // Calculate total amount
            double itemTotal = item.getPrice() * invoiceItem.getQuantity();
            totalAmount += itemTotal;

            // Create a new InvoiceItem with the item details and add it to the list
            InvoiceItem newInvoiceItem = new InvoiceItem();
            newInvoiceItem.setItem(item);
            newInvoiceItem.setQuantity(invoiceItem.getQuantity());
            newInvoiceItem.setPrice(item.getPrice());
            persistedInvoiceItems.add(newInvoiceItem);

            stock.setQuantity(stock.getQuantity() - invoiceItem.getQuantity());
            stockRepository.save(stock);
        }

        // Create the Invoice entity with calculated total amount and items
        Invoice invoice = new Invoice();
        invoice.setDate(LocalDateTime.now());
        invoice.setItems(persistedInvoiceItems);
        invoice.setTotalAmount(totalAmount);

        return invoice;
    }
}
