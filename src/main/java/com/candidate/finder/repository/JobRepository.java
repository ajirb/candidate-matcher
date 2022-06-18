package com.candidate.finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.candidate.finder.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
