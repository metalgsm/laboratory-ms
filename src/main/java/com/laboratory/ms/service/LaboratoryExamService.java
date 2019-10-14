package com.laboratory.ms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.laboratory.ms.base.service.ConflictException;
import com.laboratory.ms.base.service.ForbiddenException;
import com.laboratory.ms.model.Exam;
import com.laboratory.ms.model.Laboratory;
import com.laboratory.ms.model.LaboratoryExam;
import com.laboratory.ms.model.LaboratoryExamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LaboratoryExamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LaboratoryExamService.class);

	private static final String CONFLICT_ERROR = "Exam(name=%s) already exists";
	private static final String NOT_FOUND_ERROR = "Exam(name=%s) not found";
	private static final String NOT_FOUND_ERROR_ID = "Exam(id=%s) not found";
	private static final String FORBIDDEN_ERROR_EXAM = "Exam(id=%s) not active";
	private static final String FORBIDDEN_ERROR_LAB = "Laboratory(id=%s) not active";
	
	private LaboratoryExamRepository laboratoryExamRepository;
	private ExamService eService;
	private LaboratoryService lService;
	
	
	public void createAssociationLaboratoryToExam(Integer laboratoryId, List<Integer> examIds) {
		try {
			
			Laboratory lab = lService.findLaboratoryById(laboratoryId);
			
			if(lab.getStatus() == 0) {
				throw new ForbiddenException(String.format(FORBIDDEN_ERROR_LAB, laboratoryId));
			}
			
			examIds.forEach(examId ->{
				Exam ex = eService.findExamById(examId);
				if(ex.getStatus() == 1) {
					LaboratoryExam labE = new LaboratoryExam();
					
					labE.setLaboratoryId(laboratoryId);
					labE.setExamId(examId);
					laboratoryExamRepository.save(labE);
				}
			});
		}catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, laboratoryId));
		}
	}
	
	public void createAssociationExamToLaboratory(Integer examId, List<Integer> laboratoryIds) {
		try {
			
			Exam ex = eService.findExamById(examId);
			
			if(ex.getStatus() == 0) {
				throw new ForbiddenException(String.format(FORBIDDEN_ERROR_EXAM, examId));
			}
			
			laboratoryIds.forEach(laboratoryId ->{
				Laboratory lab = lService.findLaboratoryById(laboratoryId);
				if(lab.getStatus() == 1) {
					LaboratoryExam labE = new LaboratoryExam();
					
					labE.setExamId(examId);
					labE.setLaboratoryId(laboratoryId);
					laboratoryExamRepository.save(labE);
				}
			});
		}catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, examId));
		}
	}
	
	public void deleteAssociationLaboratoryToExam(Integer laboratoryId, List<Integer> examIds) {
		try {
			
			examIds.forEach(examId ->{
				LaboratoryExam labExam = laboratoryExamRepository.findByLaboratoryIdAndExamId(laboratoryId, examId);
				
				if(labExam != null) {
					laboratoryExamRepository.delete(labExam);
				}
			});
		}catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, laboratoryId));
		}
	}
	
	public void deleteAssociationExamToLaboratory(Integer examId, List<Integer> laboratoryIds) {
		try {
			
			laboratoryIds.forEach(laboratoryId ->{
				LaboratoryExam labExam = laboratoryExamRepository.findByLaboratoryIdAndExamId(laboratoryId, examId);
				
				if(labExam != null) {
					laboratoryExamRepository.delete(labExam);
				}
			});
		}catch (DataIntegrityViolationException e) {
			throw new ConflictException(String.format(CONFLICT_ERROR, examId));
		}
	}
	
}
