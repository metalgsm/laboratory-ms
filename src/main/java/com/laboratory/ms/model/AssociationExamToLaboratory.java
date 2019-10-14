package com.laboratory.ms.model;

import java.util.LinkedList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationExamToLaboratory {

	private Integer examId;
	
	private LinkedList<Integer> laboratoryIds;
}
