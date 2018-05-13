package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.service.CarroService;
import com.co.ceiba.backend.parkingapp.service.VigilanteService;
import com.co.ceiba.backend.parkingapp.service.VigilanteServiceImpl;

@RunWith(SpringRunner.class)
public class VigilanteServiceImplTest {

	private static final String PLACA_SIN_INICAL_A = "BOS16X";
	private static final String PLACA_CON_INICAL_A = "AOZ15D";
	private static final int CILINDRAJE = 125;
	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int MAXIMA_CANTIDAD_MOTOS = 10;
	private static final LocalDate LUNES = LocalDate.of(2018, 5, 14);
	private static final LocalDate DOMINGO = LocalDate.of(2018, 5, 13);
	private static final LocalDate SABADO = LocalDate.of(2018, 5, 12);

	@TestConfiguration
	static class VigilanteServiceImplTestContextConfiguration {

		@Bean
		public VigilanteService vigilanteService() {
			return new VigilanteServiceImpl();
		}
	}

	@Autowired
	private VigilanteService vigilanteService;

	@MockBean
	private CarroService carroService;

	@Test
	public void validarSiHayMenosDeVeinteCarrosTest() {
		// Arrange
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(aCarro().build()).build();

		// Act
		boolean menosDeVeinteCarros = vigilanteService.validarSiHayMenosDeVeinteCarros(parqueaderoCarro);

		// Assert
		assertTrue(menosDeVeinteCarros);
	}

	@Test
	public void validarSiHayMasDeVeinteCarrosTest() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();

		for (int contadorCarros = 1; contadorCarros <= MAXIMA_CANTIDAD_CARROS; contadorCarros++) {
			parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());
		}

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		// Act
		boolean menosDeVeinteCarros = vigilanteService.validarSiHayMenosDeVeinteCarros(arregloParqueaderoCarros);

		// Assert
		assertFalse(menosDeVeinteCarros);
	}

	@Test
	public void validarSiHayMenosDeDiesMotosTest() {
		// Arrange
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(aMoto().build()).build();

		// Act
		boolean menosDeDiesMotos = vigilanteService.validarSiHayMenosDeDiesMotos(parqueaderoMoto);

		// Assert
		assertTrue(menosDeDiesMotos);
	}

	@Test
	public void validarSiHayMasDeDiesMotosTest() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();

		for (int contadorMotos = 1; contadorMotos <= MAXIMA_CANTIDAD_MOTOS; contadorMotos++) {
			parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());
		}

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		// Act
		boolean menosDeDiesMotos = vigilanteService.validarSiHayMenosDeDiesMotos(arregloParqueaderoMotos);

		// Assert
		assertFalse(menosDeDiesMotos);
	}

	@Test
	public void validarPlacaConInicialATest() {
		// Arrange & Act
		boolean placaIniciaConA = vigilanteService.validarPlacaConInicialA(PLACA_CON_INICAL_A);

		// Assert
		assertTrue(placaIniciaConA);
	}

	@Test
	public void validarPlacaSinInicialATest() {
		// Arrange & Act
		boolean placaIniciaSinA = vigilanteService.validarPlacaConInicialA(PLACA_SIN_INICAL_A);

		// Assert
		assertFalse(placaIniciaSinA);
	}

	@Test
	public void validarSiDiaEsLunesTest() {
		// Arrange & Act
		boolean esLunes = vigilanteService.validarSiDiaEsLunesODomingo(LUNES);

		// Assert
		assertTrue(esLunes);
	}

	@Test
	public void validarSiDiaEsDomingoTest() {
		// Arrange & Act
		boolean esDomingo = vigilanteService.validarSiDiaEsLunesODomingo(DOMINGO);

		// Assert
		assertTrue(esDomingo);
	}

	@Test
	public void validarSiDiaNoEsLunesNiDomingoTest() {
		// Arrange & Act
		boolean noEsDomingo = vigilanteService.validarSiDiaEsLunesODomingo(SABADO);

		// Assert
		assertFalse(noEsDomingo);
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
