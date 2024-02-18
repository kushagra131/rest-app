package com.vandelay.industries.restapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="MACHINE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Machine implements Serializable {

	private static final long serialVersionUID = 4747325346342122382L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="MACHINE_OBJ_ID", unique=true, nullable=false)
	private Integer machineId;

	@Column(name="MACHINE_NAME", nullable=false, length=50)
	private String machineName;

	@Column(name="MACHINE_DESCRIPTION", length=100)
	private String machineDescription;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("machines")
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="FACTORY_OBJ_ID", nullable=false)
	private Factory factory;

}
