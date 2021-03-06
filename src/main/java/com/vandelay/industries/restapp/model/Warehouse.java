package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;


@Entity
@Table(name="WAREHOUSE")
@Data
public class Warehouse implements Serializable {

	/**
	 * serialVersionUID used for Serialization
	 */
	private static final long serialVersionUID = 2424689080134802662L;
	
	
	public Warehouse() {
		super();
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WAREHOUSE_OBJ_ID", unique=true, nullable=false)
	@ApiModelProperty(notes="Warehouse ID", name="warehouseId", required=false)
	private Integer warehouseId;
	
	
	@Column(name="WAREHOUSE_NAME", nullable=false, length=50)
	@NonNull
	@ApiModelProperty(notes="Warehouse Name", name="warehouseName", required=true, value="test warehouse name ABC")
	private String warehouseName;
	
	
	@Column(name="WAREHOUSE_DESCRIPTION", length=100)
	@ApiModelProperty(notes="Warehouse Description", name="warehouseDescription", required=false, value="test description for warehouse ABC")
	private String warehouseDescription;
	
	
	@JsonIgnoreProperties(value = { "warehouse" }, allowSetters = true)
	@OneToOne(mappedBy="warehouse", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@ApiModelProperty(notes="Warehouse Address", name="address", required=true)
	private Address address;
	
	
	public void setAddress(Address address) {
		if(address == null) {
			if(this.address != null) {
				this.address.setFactory(null);
			}
		}
		else {
			address.setWarehouse(this);
		}
		this.address = address;
	}
	
	
	@JsonIgnoreProperties("warehouse")
	@OneToOne(mappedBy="warehouse", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@PrimaryKeyJoinColumn(name="WAREHOUSE_OBJ_ID")
	@ApiModelProperty(notes="Warehouse Inventory", name="inventory", required=true)
	private Inventory inventory;
	
	
	public void setInventory(Inventory inventory) {
		if(inventory == null) {
			if(this.inventory != null) {
				this.inventory.setWarehouse(null);
			}
		}
		else {
			inventory.setWarehouse(this);
		}
		this.inventory = inventory;
	}

}
