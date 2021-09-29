package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.Factory;


public interface FactoryRepository extends JpaRepository<Factory, Integer> {

}
