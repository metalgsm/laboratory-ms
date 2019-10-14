package com.laboratory.ms.service;

import lombok.AllArgsConstructor;

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

@Service
@AllArgsConstructor
public class ExamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExamService.class);

	private static final String CONFLICT_ERROR = "Exam(name=%s) already exists";
	private static final String NOT_FOUND_ERROR = "Exam(name=%s) not found";
	private static final String NOT_FOUND_ERROR_ID = "Exam(id=%s) not found";
	
	private ExamRepository examRepository;
	
	public Exam createExam(Exam exam) {
		try {
			exam.setStatus(1);
			return examRepository.save(exam);
		}catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, exam.getName()));
		}catch (Exception e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, exam.getName()));
		}
	}
	
	public void createExam(List<Exam> exams) {
		try {
			exams.forEach(exam ->{
				exam.setStatus(1);
				examRepository.save(exam);
			});
			
		}catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, ""));
		}catch (Exception e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, ""));
		}
	}
	
	public Exam findExamByName(String name) {
		Optional<Exam> result = examRepository.findFirstByName(name);
		
		return result.orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ERROR, name)));
	}
	
	public Exam findExamById(Integer id) {
		Optional<Exam> result = examRepository.findFirstById(id);
		
		return result.orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ERROR_ID, id)));
	}
	
	public void updateExam(List<Exam> exams) {
		exams.forEach(exam ->{
			Exam current = findExamById(exam.getId());
			
			Optional.ofNullable(exam.getName()).ifPresent(name -> current.setName(name));
			Optional.ofNullable(exam.getType()).ifPresent(type -> current.setType(type));
			examRepository.save(current);
		});
		
	}
	
	public Exam updateExam(Exam exam) {
		Exam current = findExamById(exam.getId());
		
		Optional.ofNullable(exam.getName()).ifPresent(name -> current.setName(name));
		Optional.ofNullable(exam.getType()).ifPresent(type -> current.setType(type));
		
		return examRepository.save(current);
	}
	
	private Exam updateExamByName(Exam exam) {
		Exam current = findExamByName(exam.getName());
		
		Optional.ofNullable(exam.getName()).ifPresent(name -> current.setName(name));
		Optional.ofNullable(exam.getType()).ifPresent(type -> current.setType(type));
		
		return examRepository.save(current);
	}
	
	public void deleteExam(String name) {
		Exam exam = new Exam();
		exam.setName(name);
		exam.setStatus(0);
		
		updateExamByName(exam);
	}
	
	public void deleteExamsByNames(List<String> names) {
		names.forEach(name ->{
			Exam exam = new Exam();
			exam.setName(name);
			exam.setStatus(0);
			
			updateExamByName(exam);
		});
		
	}
	
	public void deleteExam(Integer id) {
		Exam exam = new Exam();
		exam.setId(id);
		exam.setStatus(0);
		
		updateExam(exam);
	}
	
	public void deleteExamsByIds(List<Integer> ids) {
		ids.forEach(id ->{
			Exam exam = new Exam();
			exam.setId(id);
			exam.setStatus(0);
			
			updateExam(exam);
		});
	}
}
