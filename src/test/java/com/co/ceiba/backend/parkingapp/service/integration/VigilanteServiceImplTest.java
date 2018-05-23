package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroDTOTestDataBuilder.aCarroDTO;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoDTOTestDataBuilder.aMotoDTO;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.CeldaParqueaderoService;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;
import com.co.ceiba.backend.parkingapp.service.VigilanteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VigilanteServiceImplTest {

	private static final String PLACA_SIN_INICIAL_A = "XOZ16D";
	private static final String PLACA_CON_INICIAL_A = "AOZ15D";
	private static final String VEHICULO_REGISTRADO = "Vehiculo registrado en el parqueadero";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int CILINDRAJE = 125;

	private static final LocalDateTime FECHA_ACTUAL = LocalDateTime.now();
	private static final LocalDateTime DOMINGO = LocalDateTime.of(2018, 5, 13, 10, 10);
	private static final LocalDateTime SABADO = LocalDateTime.of(2018, 5, 12, 10, 10);

	@Autowired
	private VigilanteService vigilanteService;

	@Autowired
	private VehiculoService<CarroDTO> carroService;

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@Autowired
	private CeldaParqueaderoService celdaParqueaderoService;

	@Test
	public void Test10CobrarRetiroCarroMenosDeUnaHoraTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 15, 6);

		vigilanteService.registrarIngresoVehiculo("KUT11L", 0, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("KUT11L", fechaRetiro);

		// Assert
		assertEquals("1000", valorTotal);
	}

	@Test
	public void Test11CobrarRetiroCarroMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 6);

		vigilanteService.registrarIngresoVehiculo("ZTU22Z", 0, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("ZTU22Z", fechaRetiro);

		// Assert
		assertEquals("3000", valorTotal);
	}

	@Test
	public void Test12CobrarRetiroCarroDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		vigilanteService.registrarIngresoVehiculo("BUE43A", 0, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("BUE43A", fechaRetiro);

		// Assert
		assertEquals("8000", valorTotal);
	}

	@Test
	public void Test13CobrarRetiroCarroDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);

		vigilanteService.registrarIngresoVehiculo("MON35A", 0, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("MON35A", fechaRetiro);

		// Assert
		assertEquals("11000", valorTotal);
	}

	@Test
	public void Test14ValidarYRegistrarIngresoCarroSinPlacaInicialAYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_SIN_INICIAL_A, 0, FECHA_ACTUAL);

		// Assert
		assertEquals(VEHICULO_REGISTRADO, mensaje);
	}

	@Test
	public void Test15ValidarYRegistrarIngresoCarroConPlacaInicialADomingoYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_CON_INICIAL_A, 0, DOMINGO);

		// Assert
		assertEquals(VEHICULO_REGISTRADO, mensaje);
	}

	// @Test
	public void Test16ValidarYRegistrarIngresoCarroSinAutorizacionTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_CON_INICIAL_A, 0, SABADO);

		// Assert
		assertEquals("El carro con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	// @Test
	public void Test17ValidarYRegistrarIngresoCarroSinEspacioTest() {
		// Arrange
		CarroDTO carro = carroService.guardarVehiculo(aCarroDTO().build());

		for (int i = 3; i <= MAXIMA_CANTIDAD_CARROS; i++) {
			CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
			celdaParqueaderoDTO.setCarro(carro);
			celdaParqueaderoService.guardarCeldaParqueadero(celdaParqueaderoDTO);
		}

		// Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_SIN_INICIAL_A, 0, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para carros en el parqueadero", mensaje);
	}

	@Test
	public void Test20CobrarRetiroMotoMenosDeUnaHoraTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 15, 6);

		vigilanteService.registrarIngresoVehiculo("HAJ00X", CILINDRAJE, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("HAJ00X", fechaRetiro);

		// Assert
		assertEquals("500", valorTotal);
	}

	@Test
	public void Test21CobrarRetiroMotoMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);

		vigilanteService.registrarIngresoVehiculo("FGL55V", CILINDRAJE, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("FGL55V", fechaRetiro);

		// Assert
		assertEquals("1500", valorTotal);
	}

	@Test
	public void Test22CobrarRetiroMotoDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		vigilanteService.registrarIngresoVehiculo("AAO02L", CILINDRAJE, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("AAO02L", fechaRetiro);

		// Assert
		assertEquals("4000", valorTotal);
	}

	@Test
	public void Test23CobrarRetiroMotoDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);

		vigilanteService.registrarIngresoVehiculo("MLP88S", CILINDRAJE, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("MLP88S", fechaRetiro);

		// Assert
		assertEquals("5500", valorTotal);
	}

	@Test
	public void Test24CobrarRetiroMotoDeUnDiaYCilindrajeMayorA500Test() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		vigilanteService.registrarIngresoVehiculo("SOS20P", 650, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroVehiculo("SOS20P", fechaRetiro);

		// Assert
		assertEquals("6000", valorTotal);
	}

	@Test
	public void Test25ValidarYRegistrarIngresoMotoSinPlacaInicialAYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals(VEHICULO_REGISTRADO, mensaje);
	}

	@Test
	public void Test26ValidarYRegistrarIngresoMotoConPlacaInicialADomingoYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_CON_INICIAL_A, CILINDRAJE, DOMINGO);

		// Assert
		assertEquals(VEHICULO_REGISTRADO, mensaje);
	}

	// @Test
	public void Test27ValidarYRegistrarIngresoMotoSinAutorizacionTest() {
		// Arrange & Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_CON_INICIAL_A, CILINDRAJE, SABADO);
		// Assert
		assertEquals("La moto con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	// @Test
	public void Test28ValidarYRegistrarIngresoMotoSinEspacioTest() {
		// Arrange
		MotoDTO moto = motoService.guardarVehiculo(aMotoDTO().build());

		for (int i = 3; i <= 20; i++) {
			CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
			celdaParqueaderoDTO.setMoto(moto);

			celdaParqueaderoService.guardarCeldaParqueadero(celdaParqueaderoDTO);
		}

		// Act
		String mensaje = vigilanteService.registrarIngresoVehiculo(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para motos en el parqueadero", mensaje);
	}

}
