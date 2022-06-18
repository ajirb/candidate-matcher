package com.candidate.finder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidate.finder.dto.CandidateRequest;
import com.candidate.finder.exception.ValidationException;
import com.candidate.finder.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test() {
		return "Hello";
	}
	
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCandidate(@PathVariable long id) throws ValidationException {
		return ResponseEntity.ok(candidateService.getCandidate(id));
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createCandidate(@RequestBody CandidateRequest Candidate) {
		return ResponseEntity.ok(candidateService.createCandidate(Candidate));
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCandidate(@PathVariable long id, @RequestBody CandidateRequest Candidate) throws ValidationException {
		return ResponseEntity.ok(candidateService.updateCandidate(id,Candidate));
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCandidate(@PathVariable long id) throws ValidationException {
		candidateService.deleteCandidate(id); 
		return ResponseEntity.ok("Deleted!");
	}
}
