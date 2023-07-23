package com.jelena.jovanovic.cocktails;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jelena.jovanovic.cocktails.dtos.CocktailDetailedResponse;
import com.jelena.jovanovic.cocktails.dtos.CocktailShortResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CocktailApiE2ETest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private List<String> cocktailNameList;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		// Set up the base URL for the API requests
		this.baseUrl = "http://localhost:" + port + "/cocktails";
	}

	@Test
	public void testGetAllCoktailsEndpoint() {

		ResponseEntity<CocktailShortResponse[]> getAllResponse = restTemplate.getForEntity(baseUrl,
				CocktailShortResponse[].class);
		assertThat(getAllResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getAllResponse.getBody()).isNotNull();
		assertThat(getAllResponse.getBody().length).isGreaterThan(0);
		List<String> listNameCoktails = Arrays.stream(getAllResponse.getBody()).map(CocktailShortResponse::getName)
				.toList();
		assertThat(listNameCoktails).containsAll(this.cocktailNameList);

	}

	@Test
	public void testGetCoktailByIdEndpoint() {

		Long cocktailId = 1l;
		ResponseEntity<CocktailDetailedResponse> getByIdResponse = restTemplate.getForEntity(baseUrl + "/" + cocktailId,
				CocktailDetailedResponse.class);
		assertThat(getByIdResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getByIdResponse.getBody()).isNotNull();
		assertThat(getByIdResponse.getBody().getId()).isEqualTo(cocktailId);

	}

	@Test
	public void testGetCoktailByNonExistentIdEndpoint() {

		Long cocktailNonExistentId = (long) 1000;
		ResponseEntity<String> getByIdErrorResponse = restTemplate.getForEntity(baseUrl + "/" + cocktailNonExistentId,
				String.class);
		assertThat(getByIdErrorResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(getByIdErrorResponse.getBody()).isEqualTo("The cocktail was not found");

	}

}
