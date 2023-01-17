package com.vandelay.industries.restapp.service;

import org.springframework.http.ResponseEntity;

import com.vandelay.industries.restapp.model.Factory;
import com.vandelay.industries.restapp.model.Machine;

public interface FactoryService {
	
	ResponseEntity<?> listFactories();
	
	ResponseEntity<?> addFactory(Factory newFactory);
	
	ResponseEntity<?> listMachinesAtFactory(String factoryId);
	
	ResponseEntity<?> addMachineAtFactory(Machine newMachine, String factoryId);

}
