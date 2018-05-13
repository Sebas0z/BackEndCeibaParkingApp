package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Moto;

public class MotoTest {
	
	public static final String PLACA = "YUI33M";
	public static final int CILINDRAJE = 125;
	
	@Test
	public void crearMotoTest() {
		// Arrange & Act
		Moto moto = aMoto().withPlaca(PLACA).withCilindraje(CILINDRAJE).build();
		
		// Assert
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(CILINDRAJE, moto.getCilindraje());
	}

}
