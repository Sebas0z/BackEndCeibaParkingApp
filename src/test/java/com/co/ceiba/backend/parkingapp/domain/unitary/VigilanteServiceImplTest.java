package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static org.junit.Assert.assertEquals;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;

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

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.service.CarroService;
import com.co.ceiba.backend.parkingapp.service.MotoService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoMotoService;
import com.co.ceiba.backend.parkingapp.service.ValidadorParqueaderoService;
import com.co.ceiba.backend.parkingapp.service.VigilanteService;
import com.co.ceiba.backend.parkingapp.service.VigilanteServiceImpl;

@RunWith(SpringRunner.class)
public class VigilanteServiceImplTest {

	private static final String PLACA_SIN_INICIAL_A = "BOS16X";
	private static final String PLACA_CON_INICIAL_A = "AOZ15D";
	private static final String CARRO_INGRESADO = "Carro registrado en el parqueadero";
	private static final String MOTO_INGRESADO = "Moto registrado en el parqueadero";
	private static final String SIN_ESPACIO_CARRO = "No hay mas espacio para carros en el parqueadero";
	private static final String SIN_ESPACIO_MOTO = "No hay mas espacio para motos en el parqueadero";
	private static final String CARRO_NO_AUTORIZADO = "El carro con placa " + PLACA_CON_INICIAL_A
			+ " no esta autorizado para ingresar al parqueadero";
	private static final String MOTO_NO_AUTORIZADO = "La moto con placa " + PLACA_CON_INICIAL_A
			+ " no esta autorizado para ingresar al parqueadero";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int MAXIMA_CANTIDAD_MOTOS = 10;
	private static final int CILINDRAJE_125 = 125;

	private static final LocalDateTime SABADO = LocalDateTime.of(2018, 5, 12, 10, 10);
	private static final LocalDateTime DOMINGO = LocalDateTime.of(2018, 5, 13, 10, 10);
	private static final LocalDateTime FECHA_ACTUAL = LocalDateTime.now();

	@TestConfiguration
	static class VigilanteServiceImplTestContextConfiguration {

		@Bean
		public VigilanteService getVigilanteService() {
			return new VigilanteServiceImpl();
		}
	}

	@Autowired
	private VigilanteService vigilanteService;

	@MockBean
	private CarroService carroService;

	@MockBean
	private MotoService motoService;

	@MockBean
	private ParqueaderoCarroService parqueaderoCarroService;

	@MockBean
	private ParqueaderoMotoService parqueaderoMotoService;

	@MockBean
	private ValidadorParqueaderoService validadorParqueaderoService;

	@Test
	public void validarYRegistrarIngresoCarroSinPlacaInicialAYEspacio() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();
		parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerTodos()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_SIN_INICIAL_A)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroConPlacaInicialADomingoYEspacio() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();
		parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerTodos()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICIAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(DOMINGO)).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, DOMINGO);

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

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerTodos()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(SIN_ESPACIO_CARRO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinAutorizacion() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();
		parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerTodos()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICIAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, SABADO);
		// Assert
		assertEquals(CARRO_NO_AUTORIZADO, mensaje);
	}

	@Test
	public void registrarIngresoCarroTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinPlacaInicialAYEspacio() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();
		parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerTodos()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_SIN_INICIAL_A)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE_125,
				FECHA_ACTUAL);

		// Assert
		assertEquals(MOTO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoConPlacaInicialADomingoYEspacio() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();
		parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerTodos()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICIAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(DOMINGO)).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE_125, DOMINGO);

		// Assert
		assertEquals(MOTO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinEspacio() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();

		for (int contadorMotos = 1; contadorMotos <= MAXIMA_CANTIDAD_MOTOS; contadorMotos++) {
			parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());
		}

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerTodos()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos))
				.thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE_125,
				FECHA_ACTUAL);

		// Assert
		assertEquals(SIN_ESPACIO_MOTO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinAutorizacion() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();
		parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerTodos()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICIAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE_125, SABADO);
		// Assert
		assertEquals(MOTO_NO_AUTORIZADO, mensaje);
	}

	@Test
	public void registrarIngresoMotoTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE_125, FECHA_ACTUAL);

		// Assert
		assertEquals(MOTO_INGRESADO, mensaje);
	}

}
