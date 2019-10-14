package com.laboratory.ms.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

	Optional<Laboratory> findByStatus(int status);

	Optional<Laboratory> findFirstByName(String name);
	
	Optional<Laboratory> findFirstById(Integer id);
}
