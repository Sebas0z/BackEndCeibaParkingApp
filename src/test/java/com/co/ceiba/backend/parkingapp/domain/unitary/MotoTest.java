package com.co.ceiba.backend.parkingapp.domain.unitary;

import static org.junit.Assert.assertEquals;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Moto;

public class MotoTest {
	
	@Test
	public void crearMotoTest() {
		
		// Arrange & Act
		Moto moto = aMoto().withPlaca("YUI33M").withCilindraje(125).build();
		// Assert
		assertEquals("YUI33M", moto.getPlaca());
		assertEquals(125, moto.getCilindraje());
		
	}

}
