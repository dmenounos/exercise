package com.example.exercise.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.exercise.model.LegalEntity;

@Repository
public class Database {

	/** In memory database of Legal Entities. */
	protected final Map<String, LegalEntity> legalEntities = new HashMap<>();

	{
		LegalEntity legalEntity = new LegalEntity();
		legalEntity.setName("LE_1");
		legalEntity.setIncorporationDate(new Date());
		legalEntity.setCountry(LegalEntity.Country.US);
		legalEntity.getShareholders().put("Share_Holder_A", 1000);
		legalEntity.getShareholders().put("Share_Holder_B", 2000);

		legalEntities.put(legalEntity.getName(), legalEntity);
	}
}
