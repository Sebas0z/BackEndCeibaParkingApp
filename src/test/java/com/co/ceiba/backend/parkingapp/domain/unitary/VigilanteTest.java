package com.co.ceiba.backend.parkingapp.domain.unitary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.ParqueoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueoMoto;
import com.co.ceiba.backend.parkingapp.domain.Vigilante;

public class VigilanteTest {
	
	private static final String PLACA = "BOS16X";
	
	@Test
	public void registrarEntradaCarroTest() {
		
		// Arrange
		Vigilante vigilante = new Vigilante();
		// Act
		ParqueoCarro parqueoCarro = vigilante.registrarParqueoCarro(PLACA);
		// Assert
		assertEquals(PLACA, parqueoCarro.getCarro().getPlaca());
		
	}
	
	@Test
	public void registrarEntradaMotoTest() {
		
		// Arrange
		Vigilante vigilante = new Vigilante();
		// Act
		ParqueoMoto parqueoMoto = vigilante.registrarParqueoMoto(PLACA, 125);
		// Assert
		assertEquals(PLACA, parqueoMoto.getMoto().getPlaca());
	}
	
	@Test
	public void registrarMasdeVeinteCarrosTest() {
		
		// Arrange
		// Act
		// Assert
		assertTrue(true);
		
	}
	
	@Test
	public void registrarMasdeDiezMotosTest() {
		
		// Arrange
		// Act
		// Assert
		assertTrue(true);
		
	}
	
	@Test
	public void registrarCarroConPlacaInicialATest() {
		// Arrange
		// Act
		// Assert
		assertTrue(true);
	}
	
	@Test
	public void registrarMotoConPlacaInicialATest() {
		// Arrange
		// Act
		// Assert
		assertTrue(true);
	}
	
	@Test
	public void cobrarParqueoCarroMenosdeNueveHorasTest() {
		// Arrange
		// Act
		// Assert
		assertTrue(true);
	}
	
	@Test
	public void cobrarParqueoMotoMenosdeNueveHorasTest() {
		// Arrange
		// Act
		// Assert
		assertTrue(true);
	}

}
