package com.vandelay.industries.restapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Entity
@Table(name="MACHINE")
@Data
@NoArgsConstructor
public class Machine implements Serializable {

	/**
	 * serialVersionUID used for Serialization
	 */
	private static final long serialVersionUID = 4747325346342122382L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MACHINE_OBJ_ID", unique=true, nullable=false)
	@ApiModelProperty(notes="Machine ID", name="machineId", required=false)
	private Integer machineId;
	
	
	@Column(name="MACHINE_NAME", nullable=false, length=50)
	@NonNull
	@ApiModelProperty(notes="Machine Name", name="machineName", required=true, value="test machine name ABC")
	private String machineName;
	
	
	@Column(name="MACHINE_DESCRIPTION", length=100)
	@ApiModelProperty(notes="Machine description", name="machineDescription", required=true, value="test machine description ABC")
	private String machineDescription;
	
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("machines")
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="FACTORY_OBJ_ID", nullable=false)
	@NonNull
	@ApiModelProperty(notes="Factory", name="factory", required=true)
	private Factory factory;

}
