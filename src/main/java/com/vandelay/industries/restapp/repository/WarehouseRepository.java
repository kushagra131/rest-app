package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.Warehouse;




public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
