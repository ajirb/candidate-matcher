package com.candidate.finder.dto;

import java.util.List;

import com.candidate.finder.model.JobSkill;

public class JobRequest {

	private String title;
	private List<JobSkill> skills;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<JobSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<JobSkill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "JobRequest [title=" + title + ", skills=" + skills + "]";
	}
}
