package com.vandelay.industries.restapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 3819889974141445220L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ADDRESS_OBJ_ID", unique=true, nullable=false)
	private Integer addressId;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("address")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FACTORY_OBJ_ID", unique=true)
	private Factory factory;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("address")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="WAREHOUSE_OBJ_ID", unique=true)
	private Warehouse warehouse;

	@Column(name="BUILDING_NAME", nullable=false, length=50)
	private String buildingName;

	@Column(name="STREET1", unique=true, nullable=false, length=100)
	private String streetLine1;

	@Column(name="STREET2", unique=true, length=50)
	private String streetLine2;

	@Column(name="CITY", nullable=false, length=25)
	private String city;

	@Column(name="STATE", nullable=false, length=25)
	private String stateProvince;

	@Column(name="ZIP_CODE", nullable=false, length=15)
	private String zipPostalCode;

	@Column(name="COUNTRY", nullable=false, length=25)
	private String country;

}
