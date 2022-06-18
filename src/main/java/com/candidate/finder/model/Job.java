package com.candidate.finder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jobIdGenerator")
	private long id;

	@Column
	private String title;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Job_id", referencedColumnName = "id")
	private List<JobSkill> skills = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
		return "Job [id=" + id + ", title=" + title + "]";
	}
}
