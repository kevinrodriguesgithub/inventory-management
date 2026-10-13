
package com.example.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventorymanagement.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
