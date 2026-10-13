
package com.example.inventorymanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(
            @RequestBody Item item) {

        Item createdItem = itemService.createItem(item);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdItem);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(
            @PathVariable Long id,
            @RequestBody Item item) {

        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id) {

        itemService.deleteItem(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/quantity")
    public Item updateQuantity(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request) {

        Integer quantity = request.get("quantity");

        return itemService.updateQuantity(id, quantity);
    }
}
