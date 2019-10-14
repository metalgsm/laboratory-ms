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
@Table(name = "LABORATORY_EXAM")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryExam implements Serializable {

	private static final long serialVersionUID = 6635416231695707808L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LBEX_ID", unique = true)
	private Integer id;
	
	@Column(name = "LBEX_LBRT_ID")
	private Integer laboratoryId;
	
	@Column(name = "LBEX_EXAM_ID")
	private Integer examId;
}
