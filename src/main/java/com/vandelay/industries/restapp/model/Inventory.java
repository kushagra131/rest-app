package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@Entity
@Table(name="INVENTORY")
@Data
public class Inventory implements Serializable {

	/**
	 * serialVersionUID used for Serialization
	 */
	private static final long serialVersionUID = -5994037680838477703L;
	
	
	public Inventory() {
		super();
	}
	
	
	@Id
    @Column(name="INVENTORY_OBJ_ID")
	@ApiModelProperty(notes="Inventory ID", name="inventoryId", required=false)
    private Integer inventoryId;
	
	
	@ToString.Exclude
	@JsonIgnoreProperties("inventory")
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name="WAREHOUSE_OBJ_ID")
	@ApiModelProperty(notes="Warehouse", name="warehouse", required=true)
	private Warehouse warehouse;
	
	
	@JsonIgnoreProperties("inventory")
	@OneToMany(mappedBy="inventory", cascade=CascadeType.ALL, orphanRemoval=true)
	@ApiModelProperty(notes="List of Inventory Items at Warehouse", name="items", required=true)
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
