package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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

	private static final String PLACA_CON_INICIAL_A = "AOZ15D";
	private static final String PLACA_SIN_INICIAL_A = "XOZ16D";
	private static final String CARRO_REGISTRADO = "Carro registrado en el parqueadero";
	private static final String MOTO_REGISTRADA = "Moto registrada en el parqueadero";

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
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(Mockito.any())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para carros en el parqueadero", mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinPlacaInicialAYEspacioTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(Mockito.any())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_REGISTRADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroConPlacaInicialADomingoYEspacioTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(Mockito.any())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(Mockito.any())).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, DOMINGO);

		// Assert
		assertEquals(CARRO_REGISTRADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinAutorizacionTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaCarro(Mockito.any())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, SABADO);

		// Assert
		assertEquals("El carro con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinEspacioTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para motos en el parqueadero", mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinPlacaInicialAYEspacioTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals(MOTO_REGISTRADA, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoConPlacaInicialADomingoYEspacioTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(DOMINGO)).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE, DOMINGO);

		// Assert
		assertEquals(MOTO_REGISTRADA, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinAutorizacionTest() {
		// Arrange
		Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		Mockito.when(validadorParqueaderoService.validarCondicionDia(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE, SABADO);
		// Assert
		assertEquals("La moto con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	@Test
	public void cobrarRetiroCarroMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);
		Carro carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withFechaIngreso(fechaIngreso).build();

		Mockito.when(carroService.obtenerCarro(Mockito.anyString())).thenReturn(carro);
		Mockito.when(parqueaderoCarroService.obtenerCarroParqueado(Mockito.any())).thenReturn(parqueaderoCarro);

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

		Mockito.when(carroService.obtenerCarro(Mockito.anyString())).thenReturn(carro);
		Mockito.when(parqueaderoCarroService.obtenerCarroParqueado(Mockito.any())).thenReturn(parqueaderoCarro);

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

		Mockito.when(carroService.obtenerCarro(Mockito.anyString())).thenReturn(carro);
		Mockito.when(parqueaderoCarroService.obtenerCarroParqueado(Mockito.any())).thenReturn(parqueaderoCarro);

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

		Mockito.when(motoService.obtenerMoto(Mockito.anyString())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(Mockito.any())).thenReturn(parqueaderoMoto);

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

		Mockito.when(motoService.obtenerMoto(Mockito.anyString())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(Mockito.any())).thenReturn(parqueaderoMoto);

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

		Mockito.when(motoService.obtenerMoto(Mockito.anyString())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(Mockito.any())).thenReturn(parqueaderoMoto);

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

		Mockito.when(motoService.obtenerMoto(Mockito.anyString())).thenReturn(moto);
		Mockito.when(parqueaderoMotoService.obtenerMotoParqueada(Mockito.any())).thenReturn(parqueaderoMoto);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroMoto(moto.getPlaca(), fechaRetiro);

		// Assert
		assertEquals("6000", valorTotal);
	}

}
