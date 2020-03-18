package com.example.exercise.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.exercise.model.LegalEntity;
import com.example.exercise.service.LegalEntityService;

@RestController
@RequestMapping("/api/legal-entities")
public class LegalEntityController {

	@Autowired
	private LegalEntityService legalEntityService;

	@GetMapping
	public List<LegalEntity> findAll() {
		return legalEntityService.findAll();
	}

	@GetMapping("/{name}")
	public LegalEntity findByName(@PathVariable String name) {
		LegalEntity legalEntity = legalEntityService.findByName(name);

		if (legalEntity == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return legalEntity;
	}

	@PostMapping
	public LegalEntity create(@RequestBody LegalEntity legalEntity) {
		return legalEntityService.create(legalEntity);
	}

	@PutMapping
	public LegalEntity update(@RequestBody LegalEntity legalEntity) {
		return legalEntityService.update(legalEntity);
	}

	@DeleteMapping("/{name}")
	public void delete(@PathVariable String name) {
		legalEntityService.delete(name);
	}
}
