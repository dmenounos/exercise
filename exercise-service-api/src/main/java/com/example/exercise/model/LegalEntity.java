package com.example.exercise.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.summingInt;

/**
 * Legal Entity object.
 */
public class LegalEntity {

	public static enum Country {
		US, UK, CH
	}

	private String name;
	private Date incorporationDate;
	private Country country;
	private Map<String, Integer> shareholders = new HashMap<>();

	public LegalEntity() {
	}

	public LegalEntity(LegalEntity other) {
		this.name = other.name;
		this.incorporationDate = other.incorporationDate;
		this.country = other.country;
		this.shareholders.putAll(other.shareholders);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIncorporationDate() {
		return incorporationDate;
	}

	public void setIncorporationDate(Date incorporationDate) {
		this.incorporationDate = incorporationDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getTotalShares() {
		return shareholders.entrySet().stream()
			.collect(summingInt(Entry<String, Integer>::getValue));
	}

	public Map<String, Integer> getShareholders() {
		return shareholders;
	}

	@Override
	public String toString() {
		return "LegalEntity ["
			+ "name=" + name + ", "
			+ "incorporationDate=" + incorporationDate + ", "
			+ "country=" + country + ", "
			+ "shareholders=" + shareholders 
			+ "]";
	}
}
