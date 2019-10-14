package com.laboratory.ms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXAM")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Exam implements Serializable {

	private static final long serialVersionUID = 7735436231695797898L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EXAM_ID", unique = true)
	private Integer id;
	
	@Column(name = "EXAM_NAME")
	private String name;
	
	@Column(name = "EXAM_TYPE")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Column(name = "EXAM_STATUS")
	private int status;
	
	public enum Type {
        CLINICAL_ANALYSIS,
        IMAGE
    }
}
