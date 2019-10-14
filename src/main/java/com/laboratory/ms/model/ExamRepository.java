package com.laboratory.ms.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	Optional<Exam> findByStatus(int status);

	Optional<Exam> findFirstByName(String name);
	
	Optional<Exam> findFirstById(Integer id);
}
