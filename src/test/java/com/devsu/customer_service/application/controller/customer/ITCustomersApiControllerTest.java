package com.devsu.customer_service.application.controller.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ITCustomersApiControllerTest {

	private static final String ID = "01K52DM7KZTHSJAKR0DT8D8H0F";
	private static final String CLIENT_ID = "POWQ110306SJ8";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		// limpieza por si hubiera registros anteriores
		jdbcTemplate.update("DELETE FROM customers WHERE client_id = ?", CLIENT_ID);
		jdbcTemplate.update("DELETE FROM persons WHERE id = ?", ID);

		// insertar persona (person)
		jdbcTemplate.update(
		  "INSERT INTO persons (id, name, gender, age, address, phone, is_active, is_deleted, created_at) " +
		  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
		  ID,
		  "Jose Lema",
		  "FEMALE",
		  35,
		  "Otavalo sn and main",
		  "+5552686590",
		  true,
		  false,
		  Timestamp.valueOf(LocalDateTime.now())
		);

		// insertar customer
		jdbcTemplate.update(
		  "INSERT INTO customers (id, client_id, encrypted_password) VALUES (?, ?, ?)",
		  ID,
		  CLIENT_ID,
		  "$2a$10$wwRQ2wix7dcMp0UGBO08POm2kZanCog4.gp9WxtLawVD6pcGe6qY2"
		);
	}

	@AfterEach
	void tearDown() {
		// borrar para dejar la DB limpia
		jdbcTemplate.update("DELETE FROM customers WHERE client_id = ?", CLIENT_ID);
		jdbcTemplate.update("DELETE FROM persons WHERE id = ?", ID);
	}

	@Test
	void getCustomerDetail_shouldReturnInsertedCustomer() throws Exception {
		mockMvc.perform(get("/customers/{id}", ID)
			.accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk())

		  .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$.id").value(ID))
		  .andExpect(jsonPath("$.client_id").value(CLIENT_ID))
		  .andExpect(jsonPath("$.name").value("Jose Lema"))
		  .andExpect(jsonPath("$.phone").value("+5552686590"))
		  .andExpect(jsonPath("$.gender").value("Female"))
		  .andExpect(jsonPath("$.age").value(35));
	}

}