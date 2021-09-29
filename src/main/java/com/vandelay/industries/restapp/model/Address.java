package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;



@Entity
@Table(name="ADDRESS")
@Data
public class Address implements Serializable {

	/**
	 * serialVersionUID used for Serialization
	 */
	private static final long serialVersionUID = 3819889974141445220L;
	
	
	public Address() {
		super();
	}
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ADDRESS_OBJ_ID", unique=true, nullable=false)
	@ApiModelProperty(notes="Address ID", name="addressId", required=false)
	private Integer addressId;
	
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("address")
	@OneToOne(/*optional=true,*/ fetch=FetchType.LAZY)
	@JoinColumn(name="FACTORY_OBJ_ID", unique=true, insertable=true, nullable=true)
	@ApiModelProperty(notes="Factory", name="factory", required=true)
	private Factory factory;
	
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("address")
	@OneToOne(/*optional=true,*/ fetch=FetchType.LAZY)
	@JoinColumn(name="WAREHOUSE_OBJ_ID", unique=true, insertable=true, nullable=true)
	@ApiModelProperty(notes="Warehouse", name="warehouse", required=true)
	private Warehouse warehouse;
	
	
	@Column(name="BUILDING_NAME", nullable=false, length=50)
	@NonNull
	@ApiModelProperty(notes="Building Name", name="buildingName", required=true, value="test building name ABC")
	private String buildingName;

	
	@Column(name="STREET1", unique=true, nullable=false, length=100)
	@NonNull
	@ApiModelProperty(notes="Street Line 1", name="streetLine1", required=true, value="test street 123 ABC")
	private String streetLine1;
	
	
	@Column(name="STREET2", unique=true, nullable=true, length=50)
	@ApiModelProperty(notes="Street Line 2", name="streetLine2", required=false, value="")
	private String streetLine2;
	
	
	@Column(name="CITY", nullable=false, length=25)
	@NonNull
	@ApiModelProperty(notes="City", name="city", required=true, value="test city XYZ")
	private String city;
	
	
	@Column(name="STATE", nullable=false, length=25)
	@NonNull
	@ApiModelProperty(notes="State", name="stateProvince", required=true, value="test state ABC")
	private String stateProvince;
	
	
	@Column(name="ZIP_CODE", nullable=false, length=15)
	@NonNull
	@ApiModelProperty(notes="Zip Code", name="zipPostalCode", required=true, value="test zip code 12345")
	private String zipPostalCode;
	
	
	@Column(name="COUNTRY", nullable=false, length=25)
	@NonNull
	@ApiModelProperty(notes="Country", name="country", required=true, value="test country USA")
	private String country;
	
}
