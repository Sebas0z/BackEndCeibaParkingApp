package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

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
import com.co.ceiba.backend.parkingapp.domain.Moto;
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

	private static final String PLACA_CON_INICAL_A = "AOZ15D";
	private static final String PLACA_SIN_INICIAL_A = "XOZ16D";
	private static final String CARRO_INGRESADO = "Carro registrado en el parqueadero";
	private static final String MOTO_INGRESADA = "Moto registrado en el parqueadero";
	private static final String SIN_ESPACIO_CARRO = "No hay mas espacio para carros en el parqueadero";
	private static final String SIN_ESPACIO_MOTO = "No hay mas espacio para motos en el parqueadero";
	private static final String CARRO_NO_AUTORIZADO = "El carro con placa " + PLACA_CON_INICAL_A
			+ " no esta autorizado para ingresar al parqueadero";
	private static final String MOTO_NO_AUTORIZADA = "La moto con placa " + PLACA_CON_INICAL_A
			+ " no esta autorizado para ingresar al parqueadero";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int MAXIMA_CANTIDAD_MOTOS = 10;
	private static final int CILINDRAJE = 125;

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
	public void validarYRegistrarIngresoCarroSinEspacioTest() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();

		for (int contadorCarros = 1; contadorCarros <= MAXIMA_CANTIDAD_CARROS; contadorCarros++) {
			parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());
		}

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerCarrosParqueados()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(SIN_ESPACIO_CARRO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinPlacaInicialAYEspacioTest() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();
		parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().build()).build());

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerCarrosParqueados()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_SIN_INICIAL_A)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroConPlacaInicialADomingoYEspacioTest() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();
		parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().withPlaca(PLACA_CON_INICAL_A).build()).build());

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerCarrosParqueados()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(DOMINGO)).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICAL_A, DOMINGO);

		// Assert
		assertEquals(CARRO_INGRESADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinAutorizacionTest() {
		// Arrange
		List<ParqueaderoCarro> parqueaderoCarros = new ArrayList<>();
		parqueaderoCarros.add(aParqueaderoCarro().withCarro(aCarro().withPlaca(PLACA_CON_INICAL_A).build()).build());

		ParqueaderoCarro[] arregloParqueaderoCarros = parqueaderoCarros
				.toArray(new ParqueaderoCarro[parqueaderoCarros.size()]);

		Mockito.when(parqueaderoCarroService.obtenerCarrosParqueados()).thenReturn(parqueaderoCarros);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarros))
				.thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICAL_A, SABADO);
		// Assert
		assertEquals(CARRO_NO_AUTORIZADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinEspacioTest() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();

		for (int contadorMotos = 1; contadorMotos <= MAXIMA_CANTIDAD_MOTOS; contadorMotos++) {
			parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());
		}

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerMotosParqueadas()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos))
				.thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals(SIN_ESPACIO_MOTO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinPlacaInicialAYEspacioTest() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();
		parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().build()).build());

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerMotosParqueadas()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_SIN_INICIAL_A)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals(MOTO_INGRESADA, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoConPlacaInicialADomingoYEspacioTest() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();
		parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().withPlaca(PLACA_CON_INICAL_A).build()).build());

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerMotosParqueadas()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(DOMINGO)).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICAL_A, CILINDRAJE, DOMINGO);

		// Assert
		assertEquals(MOTO_INGRESADA, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinAutorizacionTest() {
		// Arrange
		List<ParqueaderoMoto> parqueaderoMotos = new ArrayList<>();
		parqueaderoMotos.add(aParqueaderoMoto().withMoto(aMoto().withPlaca(PLACA_CON_INICAL_A).build()).build());

		ParqueaderoMoto[] arregloParqueaderoMotos = parqueaderoMotos
				.toArray(new ParqueaderoMoto[parqueaderoMotos.size()]);

		Mockito.when(parqueaderoMotoService.obtenerMotosParqueadas()).thenReturn(parqueaderoMotos);
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICAL_A)).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICAL_A, CILINDRAJE, SABADO);
		// Assert
		assertEquals(MOTO_NO_AUTORIZADA, mensaje);
	}

	@Test
	public void cobrarRetiroCarroMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);
		Carro carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withFechaIngreso(fechaIngreso).build();

		Mockito.when(carroService.obtenerCarro(carro.getPlaca())).thenReturn(carro);
		Mockito.when(parqueaderoCarroService.obtenerCarroParqueado(carro)).thenReturn(parqueaderoCarro);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroCarro(carro.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("3000", valorTotal);
	}

	@Test
	public void cobrarRetiroCarroDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);
		Carro carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withFechaIngreso(fechaIngreso).build();

		Mockito.when(carroService.obtenerCarro(carro.getPlaca())).thenReturn(carro);
		Mockito.when(parqueaderoCarroService.obtenerCarroParqueado(carro)).thenReturn(parqueaderoCarro);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroCarro(carro.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("8000", valorTotal);
	}

	@Test
	public void cobrarRetiroCarroDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);
		Carro carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withFechaIngreso(fechaIngreso).build();

		Mockito.when(carroService.obtenerCarro(carro.getPlaca())).thenReturn(carro);
		Mockito.when(parqueaderoCarroService.obtenerCarroParqueado(carro)).thenReturn(parqueaderoCarro);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroCarro(carro.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("11000", valorTotal);
	}

	@Test
	public void cobrarRetiroMotoMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);
		Moto moto = aMoto().build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).withFechaIngreso(fechaIngreso).build();

		Mockito.when(motoService.obtenerMoto(moto.getPlaca())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(moto)).thenReturn(parqueaderoMoto);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroMoto(moto.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("1500", valorTotal);
	}

	@Test
	public void cobrarRetiroMotoDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);
		Moto moto = aMoto().build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).withFechaIngreso(fechaIngreso).build();

		Mockito.when(motoService.obtenerMoto(moto.getPlaca())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(moto)).thenReturn(parqueaderoMoto);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroMoto(moto.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("4000", valorTotal);
	}

	@Test
	public void cobrarRetiroMotoDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);
		Moto moto = aMoto().build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).withFechaIngreso(fechaIngreso).build();

		Mockito.when(motoService.obtenerMoto(moto.getPlaca())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(moto)).thenReturn(parqueaderoMoto);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroMoto(moto.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("5500", valorTotal);
	}

	@Test
	public void cobrarRetiroMotoDeUnDiaYCilindrajeMayorA500Test() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);
		Moto moto = aMoto().withCilindraje(650).build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).withFechaIngreso(fechaIngreso).build();

		Mockito.when(motoService.obtenerMoto(moto.getPlaca())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(moto)).thenReturn(parqueaderoMoto);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroMoto(moto.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("6000", valorTotal);
	}

}
