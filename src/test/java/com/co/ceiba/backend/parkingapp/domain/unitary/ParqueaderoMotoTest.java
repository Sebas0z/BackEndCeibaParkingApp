package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public class ParqueaderoMotoTest {

	private static final String PLACA = "WJK12W";
	private static final int CILINDRAJE = 150;

	@Test
	public void crearParqueaderoMotoTest() {
		// Arrange
		Moto moto = aMoto().withPlaca(PLACA).withCilindraje(CILINDRAJE).build();
		LocalDateTime ahora = LocalDateTime.now();
		
		// Act
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).withFechaIngreso(ahora).build();
		
		// Assert
		assertEquals(ahora, parqueaderoMoto.getFechaIngreso());
		assertEquals(PLACA, parqueaderoMoto.getMoto().getPlaca());
		assertEquals(CILINDRAJE, parqueaderoMoto.getMoto().getCilindraje());
	}
	
}
