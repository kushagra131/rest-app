package com.vandelay.industries.restapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandelay.industries.restapp.exception.ResourceNotFoundException;
import com.vandelay.industries.restapp.model.Address;
import com.vandelay.industries.restapp.model.Inventory;
import com.vandelay.industries.restapp.model.InventoryItem;
import com.vandelay.industries.restapp.model.InventoryUpdate;
import com.vandelay.industries.restapp.model.Warehouse;
import com.vandelay.industries.restapp.repository.InventoryUpdateRepository;
import com.vandelay.industries.restapp.repository.WarehouseRepository;

import lombok.NonNull;



@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
	
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	
	@Autowired
	private InventoryUpdateRepository inventoryUpdateRepository;
	
	
	
	@Transactional(readOnly=true)
	@Override
	public ResponseEntity<?> listWarehouses() {
		List<Warehouse> warehouses = warehouseRepository.findAll();
		if (warehouses != null && warehouses.size() == 0)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok().body(warehouses);
	}


	
	@Transactional
	@Override
	public ResponseEntity<?> addWarehouse(@NonNull Warehouse warehouse) {
		Warehouse newWarehouse = new Warehouse();
		newWarehouse.setWarehouseName(warehouse.getWarehouseName());
		newWarehouse.setWarehouseDescription(warehouse.getWarehouseDescription());
		
		Address newAddress = new Address();
		newAddress.setBuildingName(warehouse.getAddress().getBuildingName());
		newAddress.setStreetLine1(warehouse.getAddress().getStreetLine1());
		newAddress.setStreetLine2(warehouse.getAddress().getStreetLine2());
		newAddress.setCity(warehouse.getAddress().getCity());
		newAddress.setStateProvince(warehouse.getAddress().getStateProvince());
		newAddress.setZipPostalCode(warehouse.getAddress().getZipPostalCode());
		newAddress.setCountry(warehouse.getAddress().getCountry());
		
		newWarehouse.setAddress(newAddress);
			
		Inventory newInventory = new Inventory();
		newWarehouse.setInventory(newInventory);
		
		List<InventoryItem> items = warehouse.getInventory().getItems();
		if(items != null && items.size() > 0) {
			for(InventoryItem item: items) {
				newInventory.addItem(item);
				
				InventoryUpdate itemUpdate = new InventoryUpdate();
				item.setInventoryUpdate(itemUpdate);
			}
		}
		warehouseRepository.save(newWarehouse);
		
		return ResponseEntity.created(null).body(newWarehouse);
	}


	
	@Transactional(readOnly=true)
	@Override
	public ResponseEntity<?> getWarehouse(@NonNull String warehouseId) {
		Optional<Warehouse> warehouse = Optional.ofNullable(warehouseRepository.findById(Integer.valueOf(warehouseId))
											.orElseThrow(() -> new ResourceNotFoundException("Warehouse", warehouseId)));
		return ResponseEntity.ok().body(warehouse.get());
	}


	
	@Transactional(readOnly=true)
	@Override
	public ResponseEntity<?> getInventoryAtWarehouse(@NonNull String warehouseId) {
		Optional<Warehouse> warehouse = Optional.ofNullable(warehouseRepository.findById(Integer.valueOf(warehouseId))
				.orElseThrow(() -> new ResourceNotFoundException("Warehouse", warehouseId)));
		
		return ResponseEntity.ok().body(warehouse.get().getInventory());
	}
	
	
	
	@Transactional
	@Override
	public ResponseEntity<?> addItemAtWarehouse(@NonNull InventoryItem inventoryItem,  @NonNull String warehouseId) {
		Optional<Warehouse> warehouse = Optional.ofNullable(warehouseRepository.findById(Integer.valueOf(warehouseId))
											.orElseThrow(() -> new ResourceNotFoundException("Warehouse", warehouseId)));
		
		Inventory inventory = warehouse.get().getInventory();
		inventory.addItem(inventoryItem);
		
		InventoryUpdate itemUpdate = new InventoryUpdate();
		inventoryItem.setInventoryUpdate(itemUpdate);
		
		warehouseRepository.save(warehouse.get());
		
		return ResponseEntity.created(null).body(warehouse.get());
	}


	
	@Transactional
	@Override
	public ResponseEntity<?> updateItemAtWarehouse(@NonNull InventoryUpdate itemUpdate, @NonNull String warehouseId) {
		Optional<Warehouse> warehouse = Optional.ofNullable(warehouseRepository.findById(Integer.valueOf(warehouseId))
				.orElseThrow(() -> new ResourceNotFoundException("Warehouse", warehouseId)));
		
		Inventory inventory = warehouse.get().getInventory();
		
		InventoryItem inventoryItem = inventory.getItems().stream()
								.filter(item -> item.getItemId().equals(itemUpdate.getItemId()))
								.findAny()
								.orElseThrow(() -> new ResourceNotFoundException("Inventory Item", String.valueOf(itemUpdate.getItemId())));
		
		InventoryUpdate inventoryUpdate = inventoryItem.getInventoryUpdate();
		inventoryUpdate.setItemDelete(itemUpdate.getItemDelete());
		inventoryUpdateRepository.save(inventoryUpdate);
		
		return ResponseEntity.accepted().body(warehouse.get());
	}

}
