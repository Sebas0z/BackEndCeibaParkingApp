package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotoServiceImplTest {

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@Test
	public void guardarMotoTest() {
		// Arrange
		MotoDTO moto = aMoto().build();

		// Act
		MotoDTO motoAgregado = motoService.guardarVehiculo(moto);

		// Assert
		assertEquals(moto.getPlaca(), motoAgregado.getPlaca());
		assertEquals(moto.getCilindraje(), motoAgregado.getCilindraje());

	}

	@Test
	public void obtenerMoto() {
		// Arrange
		MotoDTO moto = aMoto().build();

		// Act
		MotoDTO motoAgregado = motoService.guardarVehiculo(moto);
		MotoDTO motoObtenido = motoService.buscarVehiculoPorPlaca(motoAgregado.getPlaca());

		// Assert
		assertEquals(motoAgregado.getPlaca(), motoObtenido.getPlaca());
		assertEquals(motoAgregado.getCilindraje(), motoObtenido.getCilindraje());

	}

}
