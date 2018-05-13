package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueoCarroTestDataBuilder.aParqueoCarro;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueoCarro;

public class ParqueoCarroTest {
	
	private static final String PLACA = "COO01S";
	
	@Test
	public void crearParqueoCarroTest() {
		// Arrange
		Carro carro = aCarro().withPlaca(PLACA).build();
		LocalDateTime ahora = LocalDateTime.now();
		
		// Act
		ParqueoCarro parqueoCarro = aParqueoCarro().withCarro(carro).withFechaIngreso(ahora).build();
		
		// Assert
		assertEquals(ahora, parqueoCarro.getFechaIngreso());
		assertEquals(PLACA, parqueoCarro.getCarro().getPlaca());	
	}

}
