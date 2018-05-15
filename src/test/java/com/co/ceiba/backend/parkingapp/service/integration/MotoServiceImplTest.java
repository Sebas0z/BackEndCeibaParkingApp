package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.service.MotoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotoServiceImplTest {

	@Autowired
	private MotoService motoService;

	@Test
	public void guardarMotoTest() {
		// Arrange
		Moto moto = aMoto().build();

		// Act
		Moto motoAgregado = motoService.guardarMoto(moto);

		// Assert
		assertEquals(moto.getPlaca(), motoAgregado.getPlaca());
		assertEquals(moto.getCilindraje(), motoAgregado.getCilindraje());

	}

	@Test
	public void obtenerMoto() {
		// Arrange
		Moto motoAgregado = motoService.guardarMoto(aMoto().build());

		// Act
		Moto motoObtenido = motoService.obtenerMoto(motoAgregado.getPlaca());

		// Assert
		assertEquals(motoAgregado.getPlaca(), motoObtenido.getPlaca());
		assertEquals(motoAgregado.getCilindraje(), motoObtenido.getCilindraje());

	}

}
