package com.laboratory.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.laboratory.ms.base.service.ConflictException;
import com.laboratory.ms.base.service.NotFoundException;
import com.laboratory.ms.model.Exam;
import com.laboratory.ms.model.ExamRepository;
import com.laboratory.ms.model.Laboratory;
import com.laboratory.ms.model.LaboratoryExam;
import com.laboratory.ms.model.LaboratoryExamRepository;
import com.laboratory.ms.model.LaboratoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LaboratoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LaboratoryService.class);

	private static final String CONFLICT_ERROR = "Laboratory(name=%s) already exists";
	private static final String NOT_FOUND_ERROR = "Laboratory(name=%s) not found";
	private static final String NOT_FOUND_ERROR_EXAM = "Exam(name=%s) not found";
	private static final String NOT_FOUND_ERROR_ID = "Laboratory(id=%s) not found";

	private LaboratoryRepository laboratoryRepository;
	private ExamRepository examRepository;
	private LaboratoryExamRepository laboratoryExamRepository;

	public Laboratory createLaboratory(Laboratory laboratory) {
		try {
			laboratory.setStatus(1);
			return laboratoryRepository.save(laboratory);
		} catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, laboratory.getName()));
		} catch (Exception e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, laboratory.getName()));
		}
	}
	
	public void createLaboratory(List<Laboratory> laboratorys) {
		try {
			laboratorys.forEach(laboratory ->{
				laboratory.setStatus(1);
				laboratoryRepository.save(laboratory);
			});
		} catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, ""));
		} catch (Exception e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, ""));
		}
	}
	
	public Laboratory findLaboratoryByName(String name) {
		Optional<Laboratory> result = laboratoryRepository.findFirstByName(name);
		
		return result.orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ERROR, name)));
	}
	
	public List<Laboratory> findLaboratoryByExamName(String name) {
		Optional<Exam> ex = examRepository.findFirstByName(name);
		
		if(!ex.isPresent()) {
			throw new NotFoundException(String.format(NOT_FOUND_ERROR_EXAM, name));
		}
		
		Optional<List<LaboratoryExam>> exams = laboratoryExamRepository.findByExamId(ex.get().getId());
		
		if(!exams.isPresent()) {
			throw new NotFoundException(String.format(NOT_FOUND_ERROR_EXAM, name));
		}
		
		List<Laboratory> laboratories = new ArrayList<Laboratory>();
		
		exams.get().forEach(labEx ->{
			Optional<Laboratory> lab = laboratoryRepository.findFirstById(labEx.getLaboratoryId());
			
			if(lab.isPresent()) {
				laboratories.add(lab.get());
			}
		});
		
		return laboratories;
	}
	
	public Laboratory findLaboratoryById(Integer id) {
		Optional<Laboratory> result = laboratoryRepository.findFirstById(id);
		
		return result.orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ERROR_ID, id)));
	}
	
	public Laboratory updateLaboratory(Laboratory laboratory) {
		Laboratory current = findLaboratoryById(laboratory.getId());
		
		Optional.ofNullable(laboratory.getName()).ifPresent(name -> current.setName(name));
		Optional.ofNullable(laboratory.getAdd()).ifPresent(add -> current.setAdd(add));
		
		return laboratoryRepository.save(current);
	}
	
	public void updateLaboratory(List<Laboratory> laboratorys) {
		laboratorys.forEach(laboratory ->{
			Laboratory current = findLaboratoryById(laboratory.getId());
			
			Optional.ofNullable(laboratory.getName()).ifPresent(name -> current.setName(name));
			Optional.ofNullable(laboratory.getAdd()).ifPresent(add -> current.setAdd(add));
			
			laboratoryRepository.save(current);
		});
	}
	
	public Laboratory updateLaboratoryByName(Laboratory laboratory) {
		Laboratory current = findLaboratoryByName(laboratory.getName());
		
		Optional.ofNullable(laboratory.getName()).ifPresent(name -> current.setName(name));
		Optional.ofNullable(laboratory.getAdd()).ifPresent(add -> current.setAdd(add));
		Optional.ofNullable(laboratory.getStatus()).ifPresent(status -> current.setStatus(status));
		
		return laboratoryRepository.save(current);
	}
	
	public void deleteLaboratory(String name) {
		Laboratory laboratory = new Laboratory();
		
		laboratory.setName(name);
		laboratory.setStatus(0);
		
		updateLaboratoryByName(laboratory);
	}
	
	public void deleteLaboratory(List<String> names) {
		names.forEach(name ->{
			Laboratory laboratory = new Laboratory();
			
			laboratory.setName(name);
			laboratory.setStatus(0);
			
			updateLaboratoryByName(laboratory);
		});
	}
	
	public void deleteLaboratory(Integer id) {
		Laboratory laboratory = new Laboratory();
		
		laboratory.setId(id);
		laboratory.setStatus(0);
		
		updateLaboratory(laboratory);
	}
}
