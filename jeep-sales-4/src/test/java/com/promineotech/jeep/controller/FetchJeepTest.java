package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.promineotech.jeep.Constants;
import com.promineotech.jeep.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.sun.jdi.connect.Connector.Argument;

class FetchJeepTest {
	
	@Nested
	class TestsThatDoNotPolluteTheApplicationContext extends FetchJeepTestSupport{
	@Nested
	class TestsThatPolluteTheApplicationContext extends FetchJeepTestSupport{
	
	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("%s?model=%s&TRIM=%s", getBaseUri(), model, trim);
		
		ResponseEntity<List<Jeep>> response =  getRestTemplate().exchange(uri, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Jeep>>() {
				});
		
		
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		List<Jeep> actual = response.getBody();
		List<Jeep> expected = buildExpected();
		
		actual.forEach(jeep -> jeep.setModelPK(null));
		
		assertThat(actual).isEqualTo(expected);
	}
	@ParameterizedTest
	@MethodSource("com.promineotech.jeep.controller;.FetchJeepTest#parametersForInvalidInput")
	void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(String model, String trim, String reason) {
		
		String uri = String.format("%s?model=%s&TRIM=%s", getBaseUri(), model, trim);
		
		ResponseEntity<Map<String, Object>> response =  getRestTemplate().exchange(uri, HttpMethod.GET, null, 
				new ParameterizedTypeReference<>() {
				});
		
		
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		
		Map<String,Object> error = response.getBody();
		
		assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
	
	}
	
	
	@Test
	void testThatAnErrorMessageIsReturnedWhenAnUnknownTrimIsSupplied() {
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Unknown Value";
		String uri = String.format("%s?model=%s&TRIM=%s", getBaseUri(), model, trim);
		
		ResponseEntity<Map<String, Object>> response =  getRestTemplate().exchange(uri, HttpMethod.GET, null, 
				new ParameterizedTypeReference<>() {
				});
		
		
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		
		Map<String,Object> error = response.getBody();
		
		assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
	
	}
	
	static Stream<Arguments> parametersForInvalidInput() {
		return Stream.of(
			arguments("WRANGLER","FGBDFXGNB","Trim contains non-alpha-numeric chars") ,
			arguments("WRANGLER","C".repeat(Constants.TRIM_MAX_LENGTH + 1),"Trim length too long"),
			arguments("INVALID","Sport", "Model is not enum value")
			);
				
				
	}
}


