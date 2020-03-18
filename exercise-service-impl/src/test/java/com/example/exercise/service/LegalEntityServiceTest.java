package com.example.exercise.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.exercise.model.LegalEntity;
import com.example.exercise.model.LegalEntityBuilder;

@SpringBootTest
public class LegalEntityServiceTest {

	@Autowired
	private LegalEntityService legalEntityService;

	@Autowired
	private Database database;

	@BeforeEach
	public void init() {
		database.legalEntities.clear();
	}

	@Test
	public void createNull_ShouldThrow() {

		// Attempt to create a null reference
		assertThrows(ServiceException.class, () -> {
			legalEntityService.create(null);
		});
	}

	@Test
	public void createInvalid_ShouldThrow() {

		// Attempt to create an incomplete LegalEntity
		assertThrows(ServiceException.class, () -> {
			legalEntityService.create(new LegalEntity());
		});
	}

	@Test
	public void createDuplicate_ShouldThrow() {

		// Create a LegalEntity
		legalEntityService.create(createLegalEntity());

		// Attempt to a create a duplicate LegalEntity
		assertThrows(ServiceException.class, () -> {
			legalEntityService.create(createLegalEntity());
		});
	}

	@Test
	public void create() {

		// Create a LegalEntity
		LegalEntity legalEntity = legalEntityService.create(createLegalEntity());
		assertNotNull(legalEntity);
	}

	@Test
	public void updateNonExistent_ShouldThrow() {

		// Attempt to update a LegalEntity that does not exist
		assertThrows(ServiceException.class, () -> {
			legalEntityService.update(createLegalEntity());
		});
	}

	@Test
	public void update() {

		// Prepare a LegalEntity
		LegalEntity legalEntity1 = createLegalEntity();
		database.legalEntities.put(legalEntity1.getName(), legalEntity1);

		// Update the LegalEntity
		LegalEntity legalEntity2 = createLegalEntity();
		legalEntity2.setCountry(LegalEntity.Country.CH);
		legalEntity2 = legalEntityService.update(legalEntity2);
		assertNotNull(legalEntity2);
	}

	/*
	 * Convenience factory method.
	 */
	private static LegalEntity createLegalEntity() {
		return new LegalEntityBuilder()
			.setName("Legal Entity")
			.setIncorporationDate(new Date())
			.setCountry(LegalEntity.Country.US)
			.addShareholder("Share Holder A", 1000)
			.addShareholder("Share Holder B", 2000)
			.build();
	}
}
