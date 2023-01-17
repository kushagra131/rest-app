package com.vandelay.industries.restapp.service;

import org.springframework.http.ResponseEntity;

import com.vandelay.industries.restapp.model.InventoryItem;
import com.vandelay.industries.restapp.model.InventoryUpdate;
import com.vandelay.industries.restapp.model.Warehouse;

public interface WarehouseService {
	
	ResponseEntity<?> listWarehouses();
	
	ResponseEntity<?> addWarehouse(Warehouse newWarehouse);
	
	ResponseEntity<?> getWarehouse(String warehouseId);
	
	ResponseEntity<?> getInventoryAtWarehouse(String warehouseId);
	
	ResponseEntity<?> addItemAtWarehouse(InventoryItem inventoryItem, String warehouseId);
	
	ResponseEntity<?> updateItemAtWarehouse(InventoryUpdate itemUpdate, String warehouseId);

}
