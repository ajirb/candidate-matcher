package com.candidate.finder.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.candidate.finder.dto.CandidateRequest;
import com.candidate.finder.exception.ValidationException;
import com.candidate.finder.model.Candidate;
import com.candidate.finder.model.CandidateSkill;
import com.candidate.finder.model.Job;
import com.candidate.finder.model.JobSkill;
import com.candidate.finder.repository.CandidateRepository;
import com.candidate.finder.repository.CandidateSkillRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private CandidateSkillRepository candidateSkillRepository;

	public Candidate getCandidate(long id) throws ValidationException {
		Optional<Candidate> candidate = candidateRepository.findById(id);
		if (!candidate.isPresent())
			throw new ValidationException("Invalid Candidate id");

		return candidate.get();
	}

	public Candidate createCandidate(CandidateRequest candidate) {
		Candidate newCandidate = new Candidate();
		newCandidate.setFirstName(candidate.getFirstName());
		newCandidate.setLastName(candidate.getLastName());
		List<CandidateSkill> skills = candidate.getSkills();
		if (!CollectionUtils.isEmpty(skills)) {
			for (CandidateSkill skill : skills) {
				skill.setCandidate(newCandidate);
			}
		}
		newCandidate.setSkills(skills);

		return candidateRepository.save(newCandidate);
	}

	public Candidate updateCandidate(long id, CandidateRequest candidate) throws ValidationException {
		Candidate updatedCandidate = getCandidate(id);
		candidateSkillRepository.deleteByCandidate(updatedCandidate);
		updatedCandidate.setFirstName(candidate.getFirstName());
		updatedCandidate.setLastName(candidate.getLastName());
		List<CandidateSkill> skills = candidate.getSkills();
		if (!CollectionUtils.isEmpty(skills)) {
			for (CandidateSkill skill : skills) {
				skill.setCandidate(updatedCandidate);
			}
		}
		updatedCandidate.setSkills(skills);
		return candidateRepository.save(updatedCandidate);
	}

	public boolean deleteCandidate(long id) throws ValidationException {
		Candidate Candidate = getCandidate(id);
		candidateRepository.delete(Candidate);
		return true;
	}

	public Candidate getMatchingCandidate(Job job) throws ValidationException {
		List<JobSkill> skills = job.getSkills();
		if (CollectionUtils.isEmpty(skills)) {

		}
		Set<String> requiredSkillSet = new HashSet<>();
		for (JobSkill skill : skills) {
			requiredSkillSet.add(StringUtils.trim(skill.getSkill_name()).toUpperCase());
		}

		List<CandidateSkill> candidateSkills = candidateSkillRepository.findBySkillName(requiredSkillSet);
		if (CollectionUtils.isEmpty(candidateSkills))
			throw new ValidationException("No Candidate Found!");

		return getTopMatchingCandidate(candidateSkills);
	}

	private Candidate getTopMatchingCandidate(List<CandidateSkill> candidateSkills) {
		Map<Candidate, Integer> matchedSkillCountMap = new HashMap<>();
		for (CandidateSkill skill : candidateSkills) {
			Candidate candidate = skill.getCandidate();
			matchedSkillCountMap.put(candidate, matchedSkillCountMap.getOrDefault(candidate, 0) + 1);
		}

		PriorityQueue<CandidateMatchCount> rankedCandidates = new PriorityQueue<>(
				(a, b) -> a.matchedSkillCount == b.matchedSkillCount ? b.totalSkillCount - a.totalSkillCount
						: b.matchedSkillCount - a.matchedSkillCount); 
		for (Candidate candidate : matchedSkillCountMap.keySet()) {
			System.out.println(candidate.getId()+" "+matchedSkillCountMap.get(candidate));
			CandidateMatchCount candidateMatchCount = new CandidateMatchCount(candidate, matchedSkillCountMap.get(candidate),candidate.getSkills().size());	
			rankedCandidates.add(candidateMatchCount);
		}
		return rankedCandidates.poll().candidate;
	}

	private class CandidateMatchCount {
		Candidate candidate;
		int matchedSkillCount;
		int totalSkillCount;

		public CandidateMatchCount(Candidate candidate, int matchedSkillCount, int totalSkillCount) {
			this.matchedSkillCount = matchedSkillCount;
			this.totalSkillCount = totalSkillCount;
			this.candidate = candidate;
		}
	}
}
