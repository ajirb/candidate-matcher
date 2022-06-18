package com.candidate.finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.candidate.finder.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
