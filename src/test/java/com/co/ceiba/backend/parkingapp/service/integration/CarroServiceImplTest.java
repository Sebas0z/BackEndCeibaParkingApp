package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroDTOTestDataBuilder.aCarroDTO;
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
	public void guardarCarro() {
		// Arrange
		CarroDTO carro = aCarroDTO().build();

		// Act
		CarroDTO carroAgregado = carroService.guardarVehiculo(carro);

		// Assert
		assertEquals(carro.getPlaca(), carroAgregado.getPlaca());
	}

	@Test
	public void obtenerCarro() {
		// Arrange
		CarroDTO carro = new CarroDTO("LLOPZIU");

		// Act
		CarroDTO carroAgregado = carroService.guardarVehiculo(carro);
		CarroDTO carroObtenido = carroService.buscarVehiculoPorPlaca(carroAgregado.getPlaca());

		// Assert
		assertEquals(carroAgregado.getPlaca(), carroObtenido.getPlaca());

	}

}
