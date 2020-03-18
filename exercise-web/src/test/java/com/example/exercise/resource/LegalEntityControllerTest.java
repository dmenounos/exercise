package com.example.exercise.resource;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.exercise.model.LegalEntity;
import com.example.exercise.model.LegalEntityBuilder;
import com.example.exercise.service.LegalEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class LegalEntityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LegalEntityService legalEntityService;

	@Test
	public void test_findAll() throws Exception {

		// Prepare the mock environment
		LegalEntity mockLegalEntity1 = createLegalEntity1();
		LegalEntity mockLegalEntity2 = createLegalEntity2();

		List<LegalEntity> mockLegalEntities = Arrays.asList(
			mockLegalEntity1, mockLegalEntity2);

		when(legalEntityService.findAll())
		.thenReturn(mockLegalEntities);

		// Invoke the service
		mockMvc.perform(get("/api/legal-entities/"))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].name", is("Legal Entity 1")))
		.andExpect(jsonPath("$[0].totalShares", is(1000)))
		.andExpect(jsonPath("$[1].name", is("Legal Entity 2")))
		.andExpect(jsonPath("$[1].totalShares", is(3000)));
	}

	@Test
	public void test_findByName() throws Exception {

		// Prepare the mock environment
		LegalEntity mockLegalEntity1 = createLegalEntity1();

		when(legalEntityService.findByName(any()))
		.thenReturn(mockLegalEntity1);

		// Invoke the service
		mockMvc.perform(get("/api/legal-entities/" + mockLegalEntity1.getName()))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("Legal Entity 1")))
		.andExpect(jsonPath("$.totalShares", is(1000)));
	}

	@Test
	public void test_create() throws Exception {

		// Prepare the mock environment
		LegalEntity mockLegalEntity1 = createLegalEntity1();

		when(legalEntityService.create(any()))
		.thenReturn(mockLegalEntity1);

		// Invoke the service
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(mockLegalEntity1);

		mockMvc.perform(post("/api/legal-entities")
			.contentType(APPLICATION_JSON)
			.content(requestBody))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON));
	}

	@Test
	public void test_update() throws Exception {

		// Prepare the mock environment
		LegalEntity mockLegalEntity1 = createLegalEntity1();

		when(legalEntityService.update(any()))
		.thenReturn(mockLegalEntity1);

		// Invoke the service
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(mockLegalEntity1);

		mockMvc.perform(put("/api/legal-entities")
			.contentType(APPLICATION_JSON)
			.content(requestBody))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON));
	}

	@Test
	public void test_delete() throws Exception {

		// Prepare the mock environment
		doNothing().when(legalEntityService).delete(any());

		// Invoke the service
		mockMvc
		.perform(delete("/api/legal-entities/" + "Legal Entity 1"))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk());
	}

	/*
	 * Convenience factory method.
	 */
	private static LegalEntity createLegalEntity1() {
		return new LegalEntityBuilder()
			.setName("Legal Entity 1")
			.setIncorporationDate(new Date())
			.setCountry(LegalEntity.Country.US)
			.addShareholder("Share Holder A", 1000)
			.build();
	}

	/*
	 * Convenience factory method.
	 */
	private static LegalEntity createLegalEntity2() {
		return new LegalEntityBuilder()
			.setName("Legal Entity 2")
			.setIncorporationDate(new Date())
			.setCountry(LegalEntity.Country.UK)
			.addShareholder("Share Holder A", 1000)
			.addShareholder("Share Holder B", 2000)
			.build();
	}
}
