package com.candidate.finder.dto;

import java.util.List;

import com.candidate.finder.model.CandidateSkill;

public class CandidateRequest {
	private String firstName;
	private String lastName;
	private List<CandidateSkill> skills;

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
		return "CandidateRequest [firstName=" + firstName + ", lastName=" + lastName + ", skills=" + skills + "]";
	}

}
