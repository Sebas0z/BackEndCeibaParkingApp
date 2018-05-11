package com.co.ceiba.backend.parkingapp.domain.unitary;

import static org.junit.Assert.assertTrue;
import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder;
import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.Vigilante;

public class VigilanteTest {
	
	@Test
	public void registrarVehiculo() {
		
		// arrange
		Carro vehiculo = aCarro().build();
		Vigilante vigilante = new Vigilante();
		// act
		boolean registrado = vigilante.registrarVehiculo(vehiculo);
		// assert
		assertTrue(registrado);
		
	}

}
