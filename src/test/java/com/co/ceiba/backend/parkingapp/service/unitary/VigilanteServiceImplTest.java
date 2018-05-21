package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroDTOTestDataBuilder.aCarroDTO;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoDTOTestDataBuilder.aMotoDTO;
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
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoMotoService;
import com.co.ceiba.backend.parkingapp.service.ValidadorService;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;
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
	private VehiculoService<CarroDTO> carroService;

	@MockBean
	private VehiculoService<MotoDTO> motoService;

	@MockBean
	private ParqueaderoCarroService parqueaderoCarroService;

	@MockBean
	private ParqueaderoMotoService parqueaderoMotoService;

	@MockBean
	private ValidadorService validadorParqueaderoService;

	@Test
	public void validarYRegistrarIngresoCarroSinEspacioTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaVehiculo(Mockito.any())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para carros en el parqueadero", mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinPlacaInicialAYEspacioTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaVehiculo(Mockito.any())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_REGISTRADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroConPlacaInicialADomingoYEspacioTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaVehiculo(Mockito.any())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionDiaSegunDiasDeLaSemana(Mockito.any())).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, DOMINGO);

		// Assert
		assertEquals(CARRO_REGISTRADO, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoCarroSinAutorizacionTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaVehiculo(Mockito.any())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionDiaSegunDiasDeLaSemana(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, SABADO);

		// Assert
		assertEquals("El carro con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinEspacioTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para motos en el parqueadero", mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinPlacaInicialAYEspacioTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals(MOTO_REGISTRADA, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoConPlacaInicialADomingoYEspacioTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionDiaSegunDiasDeLaSemana(DOMINGO)).thenReturn(true);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE, DOMINGO);

		// Assert
		assertEquals(MOTO_REGISTRADA, mensaje);
	}

	@Test
	public void validarYRegistrarIngresoMotoSinAutorizacionTest() {
		// Arrange
		//Mockito.when(validadorParqueaderoService.validarSiHayEspacioParaMoto(Mockito.any())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionPlaca(Mockito.anyString())).thenReturn(true);
		//Mockito.when(validadorParqueaderoService.validarCondicionDiaSegunDiasDeLaSemana(SABADO)).thenReturn(false);

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE, SABADO);
		// Assert
		assertEquals("La moto con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

}
