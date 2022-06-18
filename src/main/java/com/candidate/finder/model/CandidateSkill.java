package com.candidate.finder.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CandidateSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "candidateSkillIdGenerator")
	@JsonIgnore
	private long id;

	@Column
	private String skill_name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "candidate_id")
	@JsonIgnore
	private Candidate candidate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "CandidateSkill [id=" + id + ", skill_name=" + skill_name + "]";
	}

}
