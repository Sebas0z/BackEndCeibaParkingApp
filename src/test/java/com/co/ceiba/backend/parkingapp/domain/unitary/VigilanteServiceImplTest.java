package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueoCarroTestDataBuilder.aParqueoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueoMotoTestDataBuilder.aParqueoMoto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.co.ceiba.backend.parkingapp.domain.ParqueoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueoMoto;
import com.co.ceiba.backend.parkingapp.domain.Vigilante;

public class VigilanteTest {

	private static final String PLACA_SIN_INICAL_A = "BOS16X";
	private static final String PLACA_CON_INICAL_A = "AOZ15D";
	private static final int CILINDRAJE = 125;

	/*@Test
	public void registrarEntradaCarroTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();

		// Act
		ParqueoCarro parqueoCarro = vigilante.registrarParqueoCarro(PLACA_SIN_INICAL_A);

		// Assert
		assertEquals(PLACA_SIN_INICAL_A, parqueoCarro.getCarro().getPlaca());
	}*/

	/*@Test
	public void registrarEntradaMotoTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();

		// Act
		ParqueoMoto parqueoMoto = vigilante.registrarParqueoMoto(PLACA_SIN_INICAL_A, CILINDRAJE);

		// Assert
		assertEquals(PLACA_SIN_INICAL_A, parqueoMoto.getMoto().getPlaca());
	}*/

	@Test
	public void validarSiHayMenosDeVeinteCarrosTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		List<ParqueoCarro> parqueoCarros = new ArrayList<>();
		parqueoCarros.add(aParqueoCarro().withCarro(aCarro().withPlaca(PLACA_SIN_INICAL_A).build()).build());

		// Act
		boolean menosDeVeinteCarros = vigilante.validarSiHayMenosDeVeinteCarros(parqueoCarros);

		// Assert
		assertTrue(menosDeVeinteCarros);
	}

	@Test
	public void validarSiHayMasDeVeinteCarrosTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		List<ParqueoCarro> parqueoCarros = new ArrayList<>();

		for (int contadorCarros = 1; contadorCarros <= 20; contadorCarros++) {
			parqueoCarros.add(aParqueoCarro().withCarro(aCarro().withPlaca(PLACA_SIN_INICAL_A).build()).build());
		}

		// Act
		boolean menosDeVeinteCarros = vigilante.validarSiHayMenosDeVeinteCarros(parqueoCarros);

		// Assert
		assertFalse(menosDeVeinteCarros);
	}

	@Test
	public void validarSiHayMenosDeDiesMotosTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		List<ParqueoMoto> parqueoMotos = new ArrayList<>();
		parqueoMotos.add(aParqueoMoto()
				.withMoto(aMoto().withPlaca(PLACA_SIN_INICAL_A).withCilindraje(CILINDRAJE).build()).build());

		// Act
		boolean menosDeDiesMotos = vigilante.validarSiHayMenosDeDiesMotos(parqueoMotos);

		// Assert
		assertTrue(menosDeDiesMotos);
	}

	@Test
	public void validarSiHayMasDeDiesMotosTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		List<ParqueoMoto> parqueoMotos = new ArrayList<>();

		for (int contadorMotos = 1; contadorMotos <= 10; contadorMotos++) {
			parqueoMotos.add(aParqueoMoto()
					.withMoto(aMoto().withPlaca(PLACA_SIN_INICAL_A).withCilindraje(CILINDRAJE).build()).build());
		}

		// Act
		boolean menosDeDiesMotos = vigilante.validarSiHayMenosDeDiesMotos(parqueoMotos);

		// Assert
		assertFalse(menosDeDiesMotos);
	}

	@Test
	public void validarPlacaConInicialATest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		
		// Act
		boolean placaIniciaConA = vigilante.validarPlacaConInicialA(PLACA_CON_INICAL_A);
		
		// Assert
		assertTrue(placaIniciaConA);
	}
	
	@Test
	public void validarPlacaSinInicialATest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		
		// Act
		boolean placaIniciaSinA = vigilante.validarPlacaConInicialA(PLACA_SIN_INICAL_A);
		
		// Assert
		assertFalse(placaIniciaSinA);
	}
	
	@Test
	public void validarSiDiaEsLunesTest() {
		// Arrange
		LocalDate lunes = LocalDate.of(2018, 5, 14);
		Vigilante vigilante = new Vigilante();
		
		// Act
		boolean esLunes = vigilante.validarSiDiaEsLunesODomingo(lunes);
		
		// Assert
		assertTrue(esLunes);
	}
	
	@Test
	public void validarSiDiaEsDomingoTest() {
		// Arrange
		LocalDate domingo = LocalDate.of(2018, 5, 13);
		Vigilante vigilante = new Vigilante();
		
		// Act
		boolean esDomingo = vigilante.validarSiDiaEsLunesODomingo(domingo);
		
		// Assert
		assertTrue(esDomingo);
	}
	
	@Test
	public void validarSiDiaNoEsLunesNiDomingoTest() {
		// Arrange
		LocalDate sabado = LocalDate.of(2018, 5, 12);
		Vigilante vigilante = new Vigilante();
		
		// Act
		boolean noEsDomingo = vigilante.validarSiDiaEsLunesODomingo(sabado);
		
		// Assert
		assertFalse(noEsDomingo);
	}

	@Test
	public void cobrarParqueoCarroMenosdeNueveHorasTest() {
		// Arrange
		Vigilante vigilante = new Vigilante();
		
		// Act
				
		
		// Assert
		assertTrue(false);
	}

	@Test
	public void cobrarParqueoMotoMenosdeNueveHorasTest() {
		// Arrange
		// Act
		// Assert
		assertTrue(true);
	}

}
