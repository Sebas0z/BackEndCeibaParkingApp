package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;

public class ParqueaderoCarroTest {
	
	private static final String PLACA = "COO01S";
	
	@Test
	public void crearParqueaderoCarroTest() {
		// Arrange
		Carro carro = aCarro().withPlaca(PLACA).build();
		LocalDateTime ahora = LocalDateTime.now();
		
		// Act
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).withFechaIngreso(ahora).build();
		
		// Assert
		assertEquals(ahora, parqueaderoCarro.getFechaIngreso());
		assertEquals(PLACA, parqueaderoCarro.getCarro().getPlaca());	
	}

}
