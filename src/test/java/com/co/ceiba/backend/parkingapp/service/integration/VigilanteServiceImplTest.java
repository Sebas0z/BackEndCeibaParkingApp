package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.service.CarroService;
import com.co.ceiba.backend.parkingapp.service.MotoService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoMotoService;
import com.co.ceiba.backend.parkingapp.service.VigilanteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VigilanteServiceImplTest {

	private static final String PLACA_SIN_INICIAL_A = "XOZ16D";
	private static final String PLACA_CON_INICIAL_A = "AOZ15D";
	private static final String CARRO_REGISTRADO = "Carro registrado en el parqueadero";
	private static final String MOTO_REGISTRADA = "Moto registrada en el parqueadero";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int CILINDRAJE = 125;

	private static final LocalDateTime FECHA_ACTUAL = LocalDateTime.now();
	private static final LocalDateTime DOMINGO = LocalDateTime.of(2018, 5, 13, 10, 10);
	private static final LocalDateTime SABADO = LocalDateTime.of(2018, 5, 12, 10, 10);

	@Autowired
	private VigilanteService vigilanteService;

	@Autowired
	private CarroService carroService;

	@Autowired
	private MotoService motoService;

	@Autowired
	private ParqueaderoCarroService parqueaderoCarroService;

	@Autowired
	private ParqueaderoMotoService parqueaderoMotoService;

	@Test
	public void Test11CobrarRetiroCarroMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);

		vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroCarro(PLACA_SIN_INICIAL_A, fechaRetiro);

		// Assert
		assertEquals("3000", valorTotal);
	}

	public void Test12CobrarRetiroCarroDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroCarro(PLACA_SIN_INICIAL_A, fechaRetiro);

		// Assert
		assertEquals("8000", valorTotal);
	}

	@Test
	public void Test13CobrarRetiroCarroDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);

		vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, fechaIngreso);

		// Act
		String valorTotal = vigilanteService.cobrarRetiroCarro(PLACA_SIN_INICIAL_A, fechaRetiro);

		// Assert
		assertEquals("11000", valorTotal);
	}

	@Test
	public void Test14ValidarYRegistrarIngresoCarroSinPlacaInicialAYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals(CARRO_REGISTRADO, mensaje);
	}

	@Test
	public void Test15ValidarYRegistrarIngresoCarroConPlacaInicialADomingoYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, DOMINGO);

		// Assert
		assertEquals(CARRO_REGISTRADO, mensaje);
	}

	@Test
	public void Test16ValidarYRegistrarIngresoCarroSinAutorizacionTest() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_CON_INICIAL_A, SABADO);

		// Assert
		assertEquals("El carro con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	@Test
	public void Test17ValidarYRegistrarIngresoCarroSinEspacioTest() {
		// Arrange
		Carro carro = carroService.guardarCarro(aCarro().build());

		for (int i = 3; i <= MAXIMA_CANTIDAD_CARROS; i++) {
			ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
			parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro);
		}

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoCarro(PLACA_SIN_INICIAL_A, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para carros en el parqueadero", mensaje);
	}

	@Test
	public void Test21ValidarYRegistrarIngresoMotoSinPlacaInicialAYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals(MOTO_REGISTRADA, mensaje);
	}

	@Test
	public void Test22ValidarYRegistrarIngresoMotoConPlacaInicialADomingoYEspacioTest() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE, DOMINGO);

		// Assert
		assertEquals(MOTO_REGISTRADA, mensaje);
	}

	@Test
	public void Test23ValidarYRegistrarIngresoMotoSinAutorizacionTest() {
		// Arrange & Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_CON_INICIAL_A, CILINDRAJE, SABADO);
		// Assert
		assertEquals("La moto con placa " + PLACA_CON_INICIAL_A + " no esta autorizado para ingresar al parqueadero",
				mensaje);
	}

	@Test
	public void Test24ValidarYRegistrarIngresoMotoSinEspacioTest() {
		// Arrange
		Moto moto = motoService.guardarMoto(aMoto().build());

		for (int i = 3; i <= 20; i++) {
			ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();
			parqueaderoMotoService.guardarParqueaderoMoto(parqueaderoMoto);
		}

		// Act
		String mensaje = vigilanteService.validarYRegistrarIngresoMoto(PLACA_SIN_INICIAL_A, CILINDRAJE, FECHA_ACTUAL);

		// Assert
		assertEquals("No hay mas espacio para motos en el parqueadero", mensaje);
	}

}
