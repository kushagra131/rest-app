package com.vandelay.industries.restapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="WAREHOUSE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse implements Serializable {

	private static final long serialVersionUID = 2424689080134802662L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="WAREHOUSE_OBJ_ID", unique=true, nullable=false)
	private Integer warehouseId;

	@Column(name="WAREHOUSE_NAME", nullable=false, length=50)
	private String warehouseName;

	@Column(name="WAREHOUSE_DESCRIPTION", length=100)
	private String warehouseDescription;

	@JsonIgnoreProperties(value = { "warehouse" }, allowSetters = true)
	@OneToOne(mappedBy="warehouse", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private Address address;

	public void setAddress(Address address) {
		if(address == null) {
			if(this.address != null)
				this.address.setFactory(null);
		} else
			address.setWarehouse(this);

		this.address = address;
	}

	@JsonIgnoreProperties("warehouse")
	@OneToOne(mappedBy="warehouse", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@PrimaryKeyJoinColumn(name="WAREHOUSE_OBJ_ID")
	private Inventory inventory;

	public void setInventory(Inventory inventory) {
		if(inventory == null) {
			if(this.inventory != null)
				this.inventory.setWarehouse(null);
		} else
			inventory.setWarehouse(this);

		this.inventory = inventory;
	}

}
