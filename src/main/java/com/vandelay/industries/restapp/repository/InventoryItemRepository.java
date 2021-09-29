package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.InventoryItem;



public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {

}
