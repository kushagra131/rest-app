package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.InventoryUpdate;

public interface InventoryUpdateRepository extends JpaRepository<InventoryUpdate, Integer> {

}
