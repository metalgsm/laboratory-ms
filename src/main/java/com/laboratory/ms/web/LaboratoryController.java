package com.laboratory.ms.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.ms.model.AssociationExamToLaboratory;
import com.laboratory.ms.model.AssociationLaboratoryToExam;
import com.laboratory.ms.model.Exam;
import com.laboratory.ms.model.Laboratory;
import com.laboratory.ms.service.ExamService;
import com.laboratory.ms.service.LaboratoryExamService;
import com.laboratory.ms.service.LaboratoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/show-case-laboratory")
@AllArgsConstructor
public class LaboratoryController {

	private LaboratoryService labService;
	private ExamService examService;
	private LaboratoryExamService labExService;
	
	@PostMapping("/exam")
	@ResponseStatus(HttpStatus.CREATED)
	public Exam createExam(@RequestBody Exam exam) {
		return examService.createExam(exam);
	}
	
	@PostMapping("/exam/lot")
	@ResponseStatus(HttpStatus.CREATED)
	public void createExam(@RequestBody List<Exam> exam) {
		examService.createExam(exam);
	}
	
	@GetMapping("/exam/{name}")
	@ResponseStatus(HttpStatus.OK)
	public Exam findExamByName(@PathVariable String name) {
		return examService.findExamByName(name);
	}
	
	@GetMapping("/exam/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Exam findExamById(@PathVariable Integer id) {
		return examService.findExamById(id);
	}
	
	@PutMapping("/exam/update")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Exam updateExam(@RequestBody Exam exam) {
		return examService.updateExam(exam);
	}
	
	@PutMapping("/exam/update/lot")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateExam(@RequestBody List<Exam> exam) {
		examService.updateExam(exam);
	}
	
	@DeleteMapping("/exam/delete/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteExam(@PathVariable String name) {
		examService.deleteExam(name);
	}
	
	@DeleteMapping("/exam/delete/lot/{names}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteExams(@PathVariable String names) {
		examService.deleteExamsByNames(Arrays.asList(names.split(",")));
	}
	
	@PostMapping("/laboratory")
	@ResponseStatus(HttpStatus.CREATED)
	public Laboratory createLaboratory(@RequestBody Laboratory laboratory) {
		return labService.createLaboratory(laboratory);
	}
	
	@PostMapping("/laboratory/lot")
	@ResponseStatus(HttpStatus.CREATED)
	public void createLaboratory(@RequestBody List<Laboratory> laboratorys) {
		labService.createLaboratory(laboratorys);
	}
	
	@GetMapping("/laboratory/{name}")
	@ResponseStatus(HttpStatus.OK)
	public Laboratory findLaboratoryByName(@PathVariable String name) {
		return labService.findLaboratoryByName(name);
	}
	
	@GetMapping("/laboratory/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Laboratory findLaboratoryById(@PathVariable Integer id) {
		return labService.findLaboratoryById(id);
	}
	
	@PutMapping("/laboratory/update")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Laboratory updateLaboratory(@RequestBody Laboratory laboratory) {
		return labService.updateLaboratory(laboratory);
	}
	
	@PutMapping("/laboratory/update/lot")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateLaboratory(@RequestBody List<Laboratory> laboratorys) {
		labService.updateLaboratory(laboratorys);
	}
	
	@DeleteMapping("/laboratory/delete/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLaboratory(@PathVariable String name) {
		labService.deleteLaboratory(name);
	}
	
	@DeleteMapping("/laboratory/delete/lot/{names}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLaboratorys(@PathVariable String names) {
		labService.deleteLaboratory(Arrays.asList(names.split(",")));
	}
	
	@PostMapping("/laboratoryExam/associationLaboratoryToExam")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void createAssociationLaboratoryToExam(@RequestBody AssociationLaboratoryToExam labToEx) {
		labExService.createAssociationLaboratoryToExam(labToEx.getLaboratoryId(), labToEx.getExamIds());
	}
	
	@PostMapping("/laboratoryExam/associationExamToLaboratory")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void createAssociationExamToLaboratory(@RequestBody AssociationExamToLaboratory exToLab) {
		labExService.createAssociationExamToLaboratory(exToLab.getExamId(), exToLab.getLaboratoryIds());
	}
	
	@DeleteMapping("/laboratoryExam/deleteAssociationLaboratoryToExam")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAssociationLaboratoryToExam(@RequestBody AssociationLaboratoryToExam labToEx) {
		labExService.deleteAssociationLaboratoryToExam(labToEx.getLaboratoryId(), labToEx.getExamIds());
	}
	
	@DeleteMapping("/laboratoryExam/deleteAssociationExamToLaboratory")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAssociationExamToLaboratory(@RequestBody AssociationExamToLaboratory exToLab) {
		labExService.deleteAssociationExamToLaboratory(exToLab.getExamId(), exToLab.getLaboratoryIds());
	}
	@GetMapping("/laboratory/exam/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<Laboratory> findLaboratoryByExam(@PathVariable String name){
		return labService.findLaboratoryByExamName(name);
	}
	
}
