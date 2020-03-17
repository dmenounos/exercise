package com.example.exercise.service;

import java.util.List;

import com.example.exercise.model.LegalEntity;

/**
 * Management interface for {@link LegalEntity} objects.
 */
public interface LegalEntityService {

	/**
	 * Finds all the {@link LegalEntity} objects.
	 */
	List<LegalEntity> findAll();

	/**
	 * Finds a {@link LegalEntity} object by matching its name property.
	 */
	LegalEntity findByName(String name);

	/**
	 * Stores a {@link LegalEntity} object. It has to be valid.
	 * 
	 * @throws {@link ServiceException} when legalEntity is not valid.
	 * @throws {@link ServiceException} when legalEntity already exists.
	 */
	LegalEntity create(LegalEntity legalEntity);

	/**
	 * Updates a {@link LegalEntity} object. It has to be valid.
	 * 
	 * @throws {@link ServiceException} when legalEntity is not valid.
	 * @throws {@link ServiceException} when legalEntity does not exist.
	 */
	LegalEntity update(LegalEntity legalEntity);

	/**
	 * Removes a {@link LegalEntity} object from the data store.
	 */
	void delete(String name);
}
