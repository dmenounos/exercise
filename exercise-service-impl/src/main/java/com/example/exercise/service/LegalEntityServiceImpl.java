package com.example.exercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exercise.model.LegalEntity;

@Service
public class LegalEntityServiceImpl implements LegalEntityService {

	@Autowired
	private Database database;

	@Override
	public List<LegalEntity> findAll() {
		return new ArrayList<LegalEntity>(getLegalEntities().values());
	}

	@Override
	public LegalEntity findByName(String name) {
		return getLegalEntities().get(name);
	}

	@Override
	public LegalEntity create(LegalEntity legalEntity) {
		validate(legalEntity);

		if (getLegalEntities().containsKey(legalEntity.getName())) {
			throw new ServiceException("legalEntity already exists");
		}

		getLegalEntities().put(legalEntity.getName(), legalEntity);
		return legalEntity;
	}

	@Override
	public LegalEntity update(LegalEntity legalEntity) {
		validate(legalEntity);

		if (!getLegalEntities().containsKey(legalEntity.getName())) {
			throw new ServiceException("legalEntity does not exist");
		}

		getLegalEntities().put(legalEntity.getName(), legalEntity);
		return legalEntity;
	}

	@Override
	public void delete(String name) {
		getLegalEntities().remove(name);
	}

	/**
	 * Convenience method.
	 * 
	 * @return the {@link Database#legalEntities} collection.
	 */
	private Map<String, LegalEntity> getLegalEntities() {
		return database.legalEntities;
	}

	/**
	 * Checks whether a {@link LegalEntity} object is valid.
	 * 
	 * @throws {@link ServiceException} when it is not.
	 */
	private void validate(LegalEntity legalEntity) {
		if (legalEntity == null) {
			throw new ServiceException("legalEntity is null");
		}

		if (legalEntity.getName() == null || legalEntity.getName().isEmpty()) {
			throw new ServiceException("legalEntity.name is empty");
		}

		if (legalEntity.getIncorporationDate() == null) {
			throw new ServiceException("legalEntity.incorporationDate is null");
		}

		if (legalEntity.getCountry() == null) {
			throw new ServiceException("legalEntity.country is null");
		}

		if (legalEntity.getTotalShares() < 0) {
			throw new ServiceException("legalEntity.totalShares is negative");
		}
	}
}
