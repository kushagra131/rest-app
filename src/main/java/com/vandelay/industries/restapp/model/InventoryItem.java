package com.vandelay.industries.restapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="INVENTORY_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem implements Serializable {

	private static final long serialVersionUID = -7750437474646963317L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ITEM_OBJ_ID", nullable=false, unique=true)
	private Integer itemId;

	@Column(name="ITEM_SKU", unique=true, nullable=false, length=10)
	private Integer itemSKU;

	@Column(name="ITEM_QUANTITY", nullable=false)
	private Integer itemQuantity;

	@Column(name="ITEM_NAME", nullable=false, length=50)
	private String itemName;

	@Column(name="ITEM_DESCRIPTION", length=100)
	private String itemDescription;

	@ToString.Exclude
	@JsonIgnoreProperties("items")
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="INVENTORY_OBJ_ID", nullable=false)
	private Inventory inventory;

	@JsonIgnoreProperties("item")
	@OneToOne(mappedBy="item", cascade=CascadeType.ALL, orphanRemoval=true)
	@PrimaryKeyJoinColumn(name="ITEM_OBJ_ID")
	private InventoryUpdate inventoryUpdate;

	public void setInventoryUpdate(InventoryUpdate inventoryUpdate) {
		if(inventoryUpdate == null) {
			if(this.inventoryUpdate != null)
				this.inventoryUpdate.setItem(null);
		} else {
			inventoryUpdate.setItemDelete(false);
			inventoryUpdate.setItem(this);
		}
		this.inventoryUpdate = inventoryUpdate;
	}

}
