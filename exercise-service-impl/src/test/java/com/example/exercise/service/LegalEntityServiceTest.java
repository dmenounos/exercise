package com.example.exercise.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.exercise.model.LegalEntity;

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
		assertThrows(ServiceException.class, () -> {
			legalEntityService.create(null);
		});
	}

	@Test
	public void createInvalid_ShouldThrow() {
		assertThrows(ServiceException.class, () -> {
			legalEntityService.create(new LegalEntity());
		});
	}

	@Test
	public void createDuplicate_ShouldThrow() {
		legalEntityService.create(createLegalEntity());

		assertThrows(ServiceException.class, () -> {
			legalEntityService.create(createLegalEntity());
		});
	}

	@Test
	public void create() {
		LegalEntity legalEntity = legalEntityService.create(createLegalEntity());
		assertNotNull(legalEntity);
	}

	@Test
	public void updateNonExistent_ShouldThrow() {
		assertThrows(ServiceException.class, () -> {
			legalEntityService.update(createLegalEntity());
		});
	}

	@Test
	public void update() {
		LegalEntity legalEntity1 = createLegalEntity();
		database.legalEntities.put(legalEntity1.getName(), legalEntity1);

		LegalEntity legalEntity2 = createLegalEntity();
		legalEntity2.setCountry(LegalEntity.Country.CH);
		legalEntity2 = legalEntityService.update(legalEntity2);
		assertNotNull(legalEntity2);
	}

	/*
	 * Factory method for Legal Entity object.
	 */
	private static LegalEntity createLegalEntity() {
		LegalEntity legalEntity = new LegalEntity();
		legalEntity.setName("NAME");
		legalEntity.setIncorporationDate(new Date());
		legalEntity.setCountry(LegalEntity.Country.US);
		legalEntity.getShareholders().put("Share Holder A", 1000);
		legalEntity.getShareholders().put("Share Holder B", 2000);
		return legalEntity;
	}
}
