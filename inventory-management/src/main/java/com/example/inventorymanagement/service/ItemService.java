package com.example.inventorymanagement.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item) {
        validateItem(item);

        item.setId(null);

        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Item not found with ID: " + id));
    }

    @Transactional
    public Item updateItem(Long id, Item updatedItem) {

        validateItem(updatedItem);

        Item existingItem = getItemById(id);

        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(
                updatedItem.getDescription());
        existingItem.setQuantity(
                updatedItem.getQuantity());
        existingItem.setPrice(updatedItem.getPrice());

        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {

        Item existingItem = getItemById(id);

        itemRepository.delete(existingItem);
    }

    @Transactional
    public Item updateQuantity(Long id, Integer quantity) {

        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative or null");
        }

        Item existingItem = getItemById(id);
        existingItem.setQuantity(quantity);

        return itemRepository.save(existingItem);
    }

    private void validateItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException(
                "Item cannot be null");
        }

        if (item.getName() == null ||
            item.getName().isBlank()) {
            throw new IllegalArgumentException(
                "Item name is required");
        }

        if (item.getQuantity() == null ||
            item.getQuantity() < 0) {
            throw new IllegalArgumentException(
                "Quantity must be zero or greater");
        }

        if (item.getPrice() == null ||
            item.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Price must be zero or greater");
        }
    }
}
