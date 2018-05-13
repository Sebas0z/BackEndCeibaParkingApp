package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.service.CarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;
import com.co.ceiba.backend.parkingapp.service.VigilanteService;
import com.co.ceiba.backend.parkingapp.service.VigilanteServiceImpl;

@RunWith(SpringRunner.class)
public class VigilanteServiceImplTest {

	private static final String PLACA_SIN_INICAL_A = "BOS16X";
	private static final String PLACA_CON_INICAL_A = "AOZ15D";
	private static final String CARRO_INGRESADO = "Carro registrado en el parqueadero";
	private static final String SIN_ESPACIO = "No hay mas espacio para carros en el parqueadero";
	private static final String NO_AUTORIZADO = "El carro con placa " + PLACA_CON_INICAL_A
			+ " no esta autorizado para ingresar al parqueadero";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int MAXIMA_CANTIDAD_MOTOS = 10;

	private static final LocalDateTime LUNES = LocalDateTime.of(2018, 5, 14, 10, 10);
	private static final LocalDateTime DOMINGO = LocalDateTime.of(2018, 5, 13, 10, 10);
	private static final LocalDateTime SABADO = LocalDateTime.of(2018, 5, 12, 10, 10);
	private static final LocalDateTime FECHA_ACTUAL = LocalDateTime.now();

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

	@MockBean
	private ParqueaderoCarroService parqueaderoCarroService;

	@Test
	public void validarYRegistrarIngresoCarroSinPlacaInicialA() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroConPlacaInicialA() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinEspacio() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();

		for (int contadorCarros = 1; contadorCarros <= MAXIMA_CANTIDAD_CARROS; contadorCarros++) {
			parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());
		}

		Mockito.when(parqueaderoCarroService.obtenerTodos()).thenReturn(parqueaderoCarros);
		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(SIN_ESPACIO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinAutorizacion() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICAL_A, SABADO);
		// Assert
		assertEquals(NO_AUTORIZADO, mensaje);
	}

	@Test
	public void registrarIngresoCarroTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoCarro(PLACA_SIN_INICAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarSiHayEspacioParaCarroTest() {
		// Arrange
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(aCarro().build()).build();

		// Act
		boolean menosDeVeinteCarros = vigilanteService.validarSiHayEspacioParaCarro(parqueaderoCarro);

		// Assert
		assertTrue(menosDeVeinteCarros);
	}

	@Test
	public void validarSiNoHayEspacioParaCarroTest() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();

		for (int contadorCarros = 1; contadorCarros <= MAXIMA_CANTIDAD_CARROS; contadorCarros++) {
			parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());
		}

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		// Act
		boolean menosDeVeinteCarros = vigilanteService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros);

		// Assert
		assertFalse(menosDeVeinteCarros);
	}

	@Test
	public void validarSiHayEspacioParaMotoTest() {
		// Arrange
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(aMoto().build()).build();

		// Act
		boolean menosDeDiesMotos = vigilanteService.validarSiHayEspacioParaMoto(parqueaderoMoto);

		// Assert
		assertTrue(menosDeDiesMotos);
	}

	@Test
	public void validarSiNoHayEspacioParaMotoTest() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();

		for (int contadorMotos = 1; contadorMotos <= MAXIMA_CANTIDAD_MOTOS; contadorMotos++) {
			parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());
		}

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		// Act
		boolean menosDeDiesMotos = vigilanteService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos);

		// Assert
		assertFalse(menosDeDiesMotos);
	}

	@Test
	public void validarSiPlacaIniciaConATest() {
		// Arrange & Act
		boolean placaIniciaConA = vigilanteService.validarCondicionPlaca(PLACA_CON_INICAL_A);

		// Assert
		assertTrue(placaIniciaConA);
	}

	@Test
	public void validarSiPlacaIniciaSinATest() {
		// Arrange & Act
		boolean placaIniciaSinA = vigilanteService.validarCondicionPlaca(PLACA_SIN_INICAL_A);

		// Assert
		assertFalse(placaIniciaSinA);
	}

	@Test
	public void validarSiDiaEsLunesTest() {
		// Arrange & Act
		boolean esLunes = vigilanteService.validarCondicionDia(LUNES);

		// Assert
		assertTrue(esLunes);
	}

	@Test
	public void validarSiDiaEsDomingoTest() {
		// Arrange & Act
		boolean esDomingo = vigilanteService.validarCondicionDia(DOMINGO);

		// Assert
		assertTrue(esDomingo);
	}

	@Test
	public void validarSiDiaNoEsLunesNiDomingoTest() {
		// Arrange & Act
		boolean noEsDomingo = vigilanteService.validarCondicionDia(SABADO);

		// Assert
		assertFalse(noEsDomingo);
	}

}
