package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name="INVENTORY_UPD")
@Data
@NoArgsConstructor
public class InventoryUpdate implements Serializable {

	/**
	 * serialVersionUID used for Serialization
	 */
	private static final long serialVersionUID = -2035951309324458451L;

	
	@Id
    @Column(name = "ITEM_OBJ_ID")
	@ApiModelProperty(notes="Inventory Item ID", name="itemId", required=false)
    private Integer itemId;
	
	
	@ToString.Exclude
	@JsonIgnoreProperties("items")
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ITEM_OBJ_ID")
	@ApiModelProperty(notes="Inventory Item", name="item", required=false)
	private InventoryItem item;
	
	
	@Column(name="ITEM_DELETE", columnDefinition="boolean default false", nullable=false)
	@ApiModelProperty(notes="Item Delete", name="itemDelete", required=true, value="false")
	private Boolean itemDelete;

}
