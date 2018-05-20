package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroServiceImplTest {

	@Autowired
	private VehiculoService<CarroDTO> carroService;

	@Test
	public void Test1guardarCarro() {
		// Arrange
		CarroDTO carro = aCarro().build();

		// Act
		CarroDTO carroAgregado = carroService.guardarVehiculo(carro);

		// Assert
		assertEquals(carro.getPlaca(), carroAgregado.getPlaca());
	}

	@Test
	public void Test2obtenerCarro() {
		// Arrange
		CarroDTO carro = aCarro().build();

		// Act
		CarroDTO carroAgregado = carroService.guardarVehiculo(carro);
		CarroDTO carroObtenido = carroService.buscarVehiculoPorPlaca(carroAgregado.getPlaca());

		// Assert
		assertEquals(carroAgregado.getPlaca(), carroObtenido.getPlaca());

	}

}
