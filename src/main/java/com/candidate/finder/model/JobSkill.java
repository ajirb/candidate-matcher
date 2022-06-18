package com.candidate.finder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class JobSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jobSkillIdGenerator")
	@JsonIgnore
	private long id;

	@Column
	private String skill_name;

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

	@Override
	public String toString() {
		return "JobSkill [id=" + id + ", skill_name=" + skill_name + "]";
	}
}
