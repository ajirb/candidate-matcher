package com.candidate.finder.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.candidate.finder.model.Candidate;
import com.candidate.finder.model.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {

	@Query("SELECT DISTINCT c FROM CandidateSkill c where UPPER(TRIM(c.skill_name)) in :skills and c.id is not null")
	List<CandidateSkill> findBySkillName(Set<String>skills);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM CandidateSkill c where c.candidate=:candidate")
	void deleteByCandidate(Candidate candidate);
}
