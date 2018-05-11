package com.co.ceiba.backend.parkingapp.domain.unitary;

import static org.junit.Assert.assertEquals;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueoCarroTestDataBuilder.aParqueoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import java.time.LocalDateTime;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.ParqueoCarro;
import com.co.ceiba.backend.parkingapp.domain.Carro;

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
