package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.Inventory;



public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
