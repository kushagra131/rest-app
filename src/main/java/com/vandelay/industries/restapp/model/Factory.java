package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="FACTORY")
@Data
@NoArgsConstructor
public class Factory implements Serializable {

	private static final long serialVersionUID = -4489104187634349291L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="FACTORY_OBJ_ID", unique=true, nullable=false)
	@ApiModelProperty(notes="Factory ID", name="factoryId", required=false)
	private Integer factoryId;

	@Column(name="FACTORY_NAME", nullable=false, length=50)
	@NonNull
	@ApiModelProperty(notes="Factory Name", name="factoryName", required=true, value="test factory ABC")
	private String factoryName;

	@Column(name="FACTORY_DESCRIPTION", length=100)
	@ApiModelProperty(notes="Factory Description", name="factoryDescription", required=false, value="test description for factory ABC")
	private String factoryDescription;

	@JsonIgnoreProperties("factory")
	@OneToOne(mappedBy="factory", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@NonNull
	@ApiModelProperty(notes="Factory Address", name="address", required=true)
	private Address address;

	public void setAddress(Address address) {
		if(address == null) {
			if(this.address != null) {
				this.address.setFactory(null);
			}
		}
		else {
			address.setFactory(this);
		}
		this.address = address;
	}

	@JsonIgnoreProperties("factory")
	@OneToMany(mappedBy="factory", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@ApiModelProperty(notes="List of Machines at Factory", name="machines", required=false)
	private List<Machine> machines = new ArrayList<>();

	public void addMachine(Machine machine) {
		machines.add(machine);
		machine.setFactory(this);
	}

	public void removeMachine(Machine machine) {
		machines.remove(machine);
		machine.setFactory(null);
	}
}
