package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Carro;

public class CarroTest {

	public static final String PLACA = "ASD10D";

	@Test
	public void crearCarroTest() {
		// Arrange & Act
		Carro carro = aCarro().withPlaca(PLACA).build();

		// Assert
		assertEquals(PLACA, carro.getPlaca());
	}

}
