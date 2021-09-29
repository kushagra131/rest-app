package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.Machine;



public interface MachineRepository extends JpaRepository<Machine, Integer> {

}
