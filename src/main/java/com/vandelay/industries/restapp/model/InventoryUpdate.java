package com.vandelay.industries.restapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="INVENTORY_UPD")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryUpdate implements Serializable {

	private static final long serialVersionUID = -2035951309324458451L;

	@Id
    @Column(name = "ITEM_OBJ_ID")
	private Integer itemId;

	@ToString.Exclude
	@JsonIgnoreProperties("items")
	@OneToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ITEM_OBJ_ID")
	private InventoryItem item;

	@Column(name="ITEM_DELETE", columnDefinition="boolean default false", nullable=false)
	private Boolean itemDelete;

}
