package com.vandelay.industries.restapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name="INVENTORY_ITEM")
@Data
@NoArgsConstructor
public class InventoryItem implements Serializable {

	private static final long serialVersionUID = -7750437474646963317L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ITEM_OBJ_ID", nullable=false, unique=true)
	@ApiModelProperty(notes="Inventory Item ID", name="itemId", required=false)
	private Integer itemId;

	@Column(name="ITEM_SKU", unique=true, nullable=false, length=10)
	@NonNull
	@ApiModelProperty(notes="Item SKU#", name="itemSKU", required=true, value="123456789")
	private Integer itemSKU;

	@Column(name="ITEM_QUANTITY", nullable=false)
	@NonNull
	@ApiModelProperty(notes="Item Quantity", name="itemQuantity", required=true, value="10")
	private Integer itemQuantity;

	@Column(name="ITEM_NAME", nullable=false, length=50)
	@NonNull
	@ApiModelProperty(notes="Item Name", name="itemName", required=true, value="test item name qwerty")
	private String itemName;

	@Column(name="ITEM_DESCRIPTION", length=100)
	@ApiModelProperty(notes="Item Description", name="itemDescription", required=false, value="test item description qwerty")
	private String itemDescription;

	@ToString.Exclude
	@JsonIgnoreProperties("items")
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="INVENTORY_OBJ_ID", nullable=false)
	@NonNull
	@ApiModelProperty(notes="Inventory", name="inventory", required=true)
	private Inventory inventory;

	@JsonIgnoreProperties("item")
	@OneToOne(mappedBy="item", cascade=CascadeType.ALL, orphanRemoval=true)
	@PrimaryKeyJoinColumn(name="ITEM_OBJ_ID")
	@ApiModelProperty(notes="Inventory Item Update", name="inventoryUpdate", required=false)
	private InventoryUpdate inventoryUpdate;

	public void setInventoryUpdate(InventoryUpdate inventoryUpdate) {
		if(inventoryUpdate == null) {
			if(this.inventoryUpdate != null) {
				this.inventoryUpdate.setItem(null);
			}
		}
		else {
			inventoryUpdate.setItemDelete(false);
			inventoryUpdate.setItem(this);
		}
		this.inventoryUpdate = inventoryUpdate;
	}
}
