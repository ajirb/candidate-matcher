package com.candidate.finder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidate.finder.dto.JobRequest;
import com.candidate.finder.exception.ValidationException;
import com.candidate.finder.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test() {
		return "Hello";
	}

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable long id) throws ValidationException {
		return ResponseEntity.ok(jobService.getJob(id));
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createJob(@RequestBody JobRequest job) {
		return ResponseEntity.ok(jobService.createJob(job));
	}

	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateJob(@PathVariable long id, @RequestBody JobRequest job) throws ValidationException {
		return ResponseEntity.ok(jobService.updateJob(id, job));
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJob(@PathVariable long id) throws ValidationException {
		jobService.deleteJob(id);
		return ResponseEntity.ok("Deleted!");
	}

	@RequestMapping(path = "/match/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMatchingJob(@PathVariable long id) throws ValidationException {
		return ResponseEntity.ok(jobService.getMatchingCandidateForAJob(id));
	}
}
