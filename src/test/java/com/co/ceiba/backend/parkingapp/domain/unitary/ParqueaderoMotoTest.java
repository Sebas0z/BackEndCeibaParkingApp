package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueoMotoTestDataBuilder.aParqueoMoto;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueoMoto;

public class ParqueoMotoTest {

	private static final String PLACA = "WJK12W";
	private static final int CILINDRAJE = 150;

	@Test
	public void crearParqueoMotoTest() {
		// Arrange
		Moto moto = aMoto().withPlaca(PLACA).withCilindraje(CILINDRAJE).build();
		LocalDateTime ahora = LocalDateTime.now();
		
		// Act
		ParqueoMoto parqueoMoto = aParqueoMoto().withMoto(moto).withFechaIngreso(ahora).build();
		
		// Assert
		assertEquals(ahora, parqueoMoto.getFechaIngreso());
		assertEquals(PLACA, parqueoMoto.getMoto().getPlaca());
		assertEquals(CILINDRAJE, parqueoMoto.getMoto().getCilindraje());
	}
	
}
