package com.vandelay.industries.restapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandelay.industries.restapp.exception.ResourceNotFoundException;
import com.vandelay.industries.restapp.model.Address;
import com.vandelay.industries.restapp.model.Factory;
import com.vandelay.industries.restapp.model.Machine;
import com.vandelay.industries.restapp.repository.FactoryRepository;

import lombok.NonNull;

@Service
@Transactional
public class FactoryServiceImpl implements FactoryService {

	private FactoryRepository factoryRepository;
	@Autowired
	public FactoryServiceImpl(FactoryRepository factoryRepository) {
		this.factoryRepository = factoryRepository;
	}

	@Override
	public ResponseEntity<?> listFactories() {
		List<Factory> factories = factoryRepository.findAll();
		if(factories != null && factories.size() == 0)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok().body(factories);
	}

	@Override
	public ResponseEntity<?> addFactory(@NonNull Factory factory) {
		
		Factory newFactory = new Factory();
		newFactory.setFactoryName(factory.getFactoryName());
		newFactory.setFactoryDescription(factory.getFactoryDescription());
		
		Address newAddress = new Address();
		newAddress.setBuildingName(factory.getAddress().getBuildingName());
		newAddress.setStreetLine1(factory.getAddress().getStreetLine1());
		newAddress.setStreetLine2(factory.getAddress().getStreetLine2());
		newAddress.setCity(factory.getAddress().getCity());
		newAddress.setStateProvince(factory.getAddress().getStateProvince());
		newAddress.setZipPostalCode(factory.getAddress().getZipPostalCode());
		newAddress.setCountry(factory.getAddress().getCountry());
		
		newFactory.setAddress(newAddress);
		
		List<Machine> machines = factory.getMachines();
		if(machines != null && machines.size() > 0) {
			for(Machine machine: machines) {
				newFactory.addMachine(machine);
				
			}
		}
		factoryRepository.save(newFactory);
		
		return ResponseEntity.created(null).body(newFactory);
	}

	@Override
	public ResponseEntity<?> addMachineAtFactory(@NonNull Machine machine, @NonNull String factoryId) {
		Optional<Factory> factory = Optional.ofNullable(factoryRepository.findById(Integer.valueOf(factoryId))
				.orElseThrow(() -> new ResourceNotFoundException("Factory", factoryId)));
		
		Machine newMachine = new Machine();
		newMachine.setMachineName(machine.getMachineName());
		newMachine.setMachineDescription(machine.getMachineDescription());
		factory.get().addMachine(newMachine);
		
		return ResponseEntity.created(null).body(factoryRepository.save(factory.get()));
	}

	@Override
	public ResponseEntity<?> listMachinesAtFactory(@NonNull String factoryId) {
		Optional<Factory> factory = Optional.ofNullable(factoryRepository.findById(Integer.valueOf(factoryId))
										.orElseThrow(() -> new ResourceNotFoundException("Factory", factoryId)));
		
		return ResponseEntity.ok().body(factory.get().getMachines());
	}
}
