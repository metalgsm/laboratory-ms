package com.laboratory.ms.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryExamRepository extends JpaRepository<LaboratoryExam, Long>{

	Optional<List<LaboratoryExam>> findByLaboratoryId(Integer laboratoryId);
	
	LaboratoryExam findByLaboratoryIdAndExamId(Integer laboratoryId, Integer examId);
	
	Optional<List<LaboratoryExam>> findByExamId(Integer examId);
}
