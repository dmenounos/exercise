package com.example.exercise.model;

import java.util.Date;

import com.example.exercise.model.LegalEntity.Country;

/**
 * Builder of Legal Entity objects.
 */
public class LegalEntityBuilder {

	private LegalEntity prototype = new LegalEntity();

	public LegalEntityBuilder setName(String name) {
		prototype.setName(name);
		return this;
	}

	public LegalEntityBuilder setIncorporationDate(Date incorporationDate) {
		prototype.setIncorporationDate(incorporationDate);
		return this;
	}

	public LegalEntityBuilder setCountry(Country country) {
		prototype.setCountry(country);
		return this;
	}

	public LegalEntityBuilder addShareholder(String shareholderName, Integer noShares) {
		prototype.getShareholders().put(shareholderName, noShares);
		return this;
	}

	public LegalEntity build() {
		return new LegalEntity(prototype);
	}
}
