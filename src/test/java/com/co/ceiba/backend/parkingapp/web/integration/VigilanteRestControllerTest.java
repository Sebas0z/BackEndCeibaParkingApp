package com.co.ceiba.backend.parkingapp.web.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VigilanteRestControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void ingresarCarro() throws Exception {
		// Arrange & Act & Assert
		assertThat(this.restTemplate
				.getForObject(("http://localhost:" + port + "/vigilante/ingresarCarro?placa=COX13N"), String.class))
						.contains("Carro registrado en el parqueadero");
	}

	@Test
	public void ingresarMoto() throws Exception {
		// Arrange & Act & Assert
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/ingresarMoto?placa=ZOM12H&cilindraje=125"), String.class))
						.contains("Moto registrada en el parqueadero");
	}

	@Test
	public void cobrarRetiroCarro() throws Exception {
		// Arrange
		assertThat(this.restTemplate
				.getForObject(("http://localhost:" + port + "/vigilante/ingresarCarro?placa=MAT07Z"), String.class))
						.contains("Carro registrado en el parqueadero");

		// Act & Assert
		assertThat(this.restTemplate
				.getForObject(("http://localhost:" + port + "/vigilante/cobrarRetiroCarro?placa=MAT07Z"), String.class))
						.contains("1000");
	}

	@Test
	public void cobrarRetiroMoto() throws Exception {
		// Arrange
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/ingresarMoto?placa=KAP33U&cilindraje=125"), String.class))
						.contains("Moto registrada en el parqueadero");

		// Act & Assert
		assertThat(this.restTemplate
				.getForObject(("http://localhost:" + port + "/vigilante/cobrarRetiroMoto?placa=AAP33U"), String.class))
						.contains("500");

	}

}
