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
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/registrarIngresoVehiculo?placa=COX13N"), String.class))
						.contains("Vehiculo registrado en el parqueadero");
	}

	@Test
	public void ingresarMoto() throws Exception {
		// Arrange & Act & Assert
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/registrarIngresoVehiculo?placa=ZOM12H&cilindraje=125"),
				String.class)).contains("Vehiculo registrado en el parqueadero");
	}

	@Test
	public void cobrarRetiroCarro() throws Exception {
		// Arrange
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/registrarIngresoVehiculo?placa=MAT07Z"), String.class))
						.contains("Vehiculo registrado en el parqueadero");

		Thread.sleep(15000);

		// Act & Assert
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/cobrarRetiroVehiculo?placa=MAT07Z"), String.class))
						.contains("1000");
	}

	@Test
	public void cobrarRetiroMoto() throws Exception {
		// Arrange
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/registrarIngresoVehiculo?placa=KAP33U&cilindraje=125"),
				String.class)).contains("Vehiculo registrado en el parqueadero");

		Thread.sleep(15000);

		// Act & Assert
		assertThat(this.restTemplate.getForObject(
				("http://localhost:" + port + "/vigilante/cobrarRetiroVehiculo?placa=AAP33U"), String.class))
						.contains("500");

	}

}
