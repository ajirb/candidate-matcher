package com.candidate.finder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "candidateIdGenerator")
	private long id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
	private List<CandidateSkill> skills = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<CandidateSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<CandidateSkill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", skills=" + skills
				+ "]";
	}

}
