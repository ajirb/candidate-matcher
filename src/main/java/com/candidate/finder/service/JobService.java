package com.candidate.finder.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidate.finder.dto.JobRequest;
import com.candidate.finder.exception.ValidationException;
import com.candidate.finder.model.Candidate;
import com.candidate.finder.model.Job;
import com.candidate.finder.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private CandidateService candidateService;

	public Job getJob(long id) throws ValidationException {
		Optional<Job> job = jobRepository.findById(id);
		if (!job.isPresent())
			throw new ValidationException("Invalid Job id");

		return job.get();
	}

	public Job createJob(JobRequest job) {
		Job newJob = new Job();
		newJob.setTitle(job.getTitle());
		newJob.setSkills(job.getSkills());
		return jobRepository.save(newJob);
	}

	public Job updateJob(long id, JobRequest job) throws ValidationException {
		Job updatedJob = getJob(id);
		updatedJob.setTitle(job.getTitle());
		updatedJob.setSkills(job.getSkills());

		return jobRepository.save(updatedJob);
	}

	public boolean deleteJob(long id) throws ValidationException {
		Job job = getJob(id);
		jobRepository.delete(job);
		return true;
	}

	public Candidate getMatchingCandidateForAJob(long jobId) throws ValidationException {
		Job job = getJob(jobId);

		return candidateService.getMatchingCandidate(job);
	}
}
