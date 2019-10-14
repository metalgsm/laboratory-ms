package com.laboratory.ms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LABORATORY")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory implements Serializable {
	private static final long serialVersionUID = 7735416231695707808L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LBRT_ID", unique = true)
	private Integer id;

	@Column(name = "LBRT_NAME")
	private String name;
	
	@Column(name = "LBRT_ADD")
	private String add;
	
	@Column(name = "LBRT_STATUS")
	private int status; 
}
