package com.vandelay.industries.restapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="INVENTORY_UPD")
@Data
@NoArgsConstructor
public class InventoryUpdate implements Serializable {

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
