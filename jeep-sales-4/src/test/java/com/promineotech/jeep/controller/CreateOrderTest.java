package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.net.http.HttpHeaders;

import org.apache.catalina.loader.ResourceEntry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.promineotech.jeep.controller.support.CreateOrderTestSupport;
import com.promineotech.jeep.entity.JeepModel;

import io.swagger.v3.oas.models.media.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
	scripts = {"classpath:flyway/migrations/V1.0__jeep_Schema.sql",
			"classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
	config = @SqlConfig(encoding = "utf-8"))
	

class CreateOrderTest extends CreateOrderTestSupport{

	@Test
	void testCreateOrderReturnsSuccess201() {
		String body = "";
		String uri = getBaseUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		HttpEntity<String> bodyEntity = new HttpEntity<>(body);
		
		ResponseEntity<Order> response = getRestTemplate().exchange(uri,
				HttpMethod.POST, bodyEntity, Order.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		assertThat(response.getBody()).isNotNull();
		
		Orderr order = response.getBody();
		assertThat(order.getCustomer().getCustomerId()).isEquealTo("MORISON_LINA");
		assertThat(order.getModel().getModelId()).isEquealTo(JeepModel.WRANGLER);
		assertThat(order.getModel().getTrimLevel()).isEquealTo("Sport Altitude");
		assertThat(order.getModel().getNumDoors()).isEquealTo(4);
		assertThat(order.getColor().getColorId()).isEquealTo("EXT_NACHO");
		assertThat(order.getEngine().getEngineId()).isEquealTo("2_0_TURBO");
		assertThat(order.getTire().getTireId()).isEquealTo("35_TOYO");
		assertThat(order.getOptions()).hasSize(6);
	}

}
