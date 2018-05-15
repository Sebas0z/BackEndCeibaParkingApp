package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.service.CarroService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroServiceImplTest {

	@Autowired
	private CarroService carroService;

	@Test
	public void guardarCarro() {
		// Arrange
		Carro carro = aCarro().build();

		// Act
		Carro carroAgregado = carroService.guardarCarro(carro);

		// Assert
		assertEquals(carro.getPlaca(), carroAgregado.getPlaca());
	}

	@Test
	public void obtenerCarro() {
		// Arrange
		Carro carroAgregado = carroService.guardarCarro(aCarro().build());

		// Act
		Carro carroObtenido = carroService.obtenerCarro(carroAgregado.getPlaca());

		// Assert
		assertEquals(carroAgregado.getPlaca(), carroObtenido.getPlaca());

	}

}
