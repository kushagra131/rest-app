package com.vandelay.industries.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandelay.industries.restapp.constants.AppUrlConstants;
import com.vandelay.industries.restapp.model.Factory;
import com.vandelay.industries.restapp.model.Machine;
import com.vandelay.industries.restapp.service.FactoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(AppUrlConstants.FACTORY_API)
@Api(value="factory", description="Factory Rest API Endpoints")
public class FactoryController {

	private FactoryService factoryService;
	@Autowired
	public FactoryController(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	@ApiOperation(value = "Gets a list of Factories from the System ", response = ResponseEntity.class)
	@GetMapping(AppUrlConstants.LIST_FACTORIES)
	ResponseEntity<?> factories() {
		return factoryService.listFactories();
	}

	@ApiOperation(value = "Adds a Factory in the System ", response = ResponseEntity.class)
	@PostMapping(AppUrlConstants.ADD_FACTORY)
	ResponseEntity<?> addFactory(@RequestBody Factory newFactory) {
		return factoryService.addFactory(newFactory);
	}

	@ApiOperation(value = "Gets a list of Machine at a single Factory from the System ", response = ResponseEntity.class)
	@GetMapping(AppUrlConstants.LIST_MACHINES_AT_FACTORY)
	ResponseEntity<?> machinesAtFactory(@PathVariable String factoryId) {
		return factoryService.listMachinesAtFactory(factoryId);
	}

	@ApiOperation(value = "Adds a Machine at a single Factory from the System ", response = ResponseEntity.class)
	@PostMapping(AppUrlConstants.ADD_MACHINE_AT_FACTORY)
	ResponseEntity<?> addMachineAtFactory(@RequestBody Machine newMachine, @PathVariable String factoryId) {
		return factoryService.addMachineAtFactory(newMachine, factoryId);
	}

}
