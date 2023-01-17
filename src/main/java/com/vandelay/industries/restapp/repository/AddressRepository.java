package com.vandelay.industries.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandelay.industries.restapp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
