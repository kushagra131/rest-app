package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="INVENTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {

	private static final long serialVersionUID = -5994037680838477703L;

	@Id
    @Column(name="INVENTORY_OBJ_ID")
	private Integer inventoryId;

	@ToString.Exclude
	@JsonIgnoreProperties("inventory")
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name="WAREHOUSE_OBJ_ID")
	private Warehouse warehouse;
	
	@JsonIgnoreProperties("inventory")
	@OneToMany(mappedBy="inventory", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<InventoryItem> items = new ArrayList<>();

	public void addItem(InventoryItem item) {
		items.add(item);
		item.setInventory(this);
	}
	
	public void removeItem(InventoryItem item) {
		items.remove(item);
		item.setInventory(null);
	}

}
