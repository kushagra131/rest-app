package com.vandelay.industries.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandelay.industries.restapp.constants.AppUrlConstants;
import com.vandelay.industries.restapp.model.InventoryItem;
import com.vandelay.industries.restapp.model.InventoryUpdate;
import com.vandelay.industries.restapp.model.Warehouse;
import com.vandelay.industries.restapp.service.WarehouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(AppUrlConstants.WAREHOUSE_API)
@Api(value="warehouse", description="Warehouse Rest API Endpoints")
public class WarehouseController {

	private WarehouseService warehouseService;
	@Autowired
	public WarehouseController(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@ApiOperation(value = "Gets a list of Warehouses in the System ", response = ResponseEntity.class)
    @GetMapping(AppUrlConstants.LIST_WAREHOUSES)
	ResponseEntity<?> listWarehouses() {
		return warehouseService.listWarehouses();
	}

	@ApiOperation(value = "Adds a Warehouse in the System ", response = ResponseEntity.class)
	@PostMapping(AppUrlConstants.ADD_WAREHOUSE)
	ResponseEntity<?> addWarehouse(@RequestBody Warehouse warehouse) {
		return warehouseService.addWarehouse(warehouse);
	}

	@ApiOperation(value = "Gets a Warehouse from the System ", response = ResponseEntity.class)
	@GetMapping(AppUrlConstants.GET_WAREHOUSE)
	ResponseEntity<?> listWarehouse(@PathVariable String warehouseId) {
		return warehouseService.getWarehouse(warehouseId);
	}

	@ApiOperation(value = "Gets an Inventory of a single Warehouses from the System ", response = ResponseEntity.class)
	@GetMapping(AppUrlConstants.LIST_INVENTORY_AT_WAREHOUSE)
	ResponseEntity<?> getInventory(@PathVariable String warehouseId) {
		return warehouseService.getInventoryAtWarehouse(warehouseId);
	}

	@ApiOperation(value = "Adds an Inventory Item at a single Warehouses in the System ", response = ResponseEntity.class)
	@PostMapping(AppUrlConstants.ADD_INVENTORY_AT_WAREHOUSE)
	ResponseEntity<?> addItemAtWarehouse(@RequestBody InventoryItem item, @PathVariable String warehouseId) {
		return warehouseService.addItemAtWarehouse(item, warehouseId);
	}

	@ApiOperation(value = "Updates an Inventory Item at a single Warehouses in the System ", response = ResponseEntity.class)
	@PatchMapping(AppUrlConstants.UPDATE_INVENTORY_AT_WAREHOUSE)
	ResponseEntity<?> updateItemAtWarehouse(@RequestBody InventoryUpdate itemUpdate, @PathVariable String warehouseId) {
		return warehouseService.updateItemAtWarehouse(itemUpdate, warehouseId);
	}
}
