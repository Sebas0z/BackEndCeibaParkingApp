package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoDTOTestDataBuilder.aMotoDTO;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.CobrarService;
import com.co.ceiba.backend.parkingapp.service.CobrarServiceImpl;

@RunWith(SpringRunner.class)
public class CobrarServiceImplTest {

	private static final int IMPUESTO_CILINDRAJE_MAXIMO = 2000;
	private static final int CILINDRAJE_MAXIMO = 500;
	private static final int HORAS_MINIMAS_COBRO_HORA = 9;
	private static final int VALOR_MOTO_DIA = 4000;
	private static final int VALOR_MOTO_HORA = 500;
	private static final int VALOR_CARRO_DIA = 8000;
	private static final int VALOR_CARRO_HORA = 1000;

	@TestConfiguration
	static class CobrarServiceImplTestContextConfiguration {

		@Bean
		public CobrarService getCobrarService() {
			return new CobrarServiceImpl();
		}
	}

	@Autowired
	private CobrarService cobrarService;

	@Test
	public void cobrarRetiroCarroMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_CARRO_HORA,
				VALOR_CARRO_DIA, HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(3000, resultado);
	}

	@Test
	public void cobrarRetiroCarroDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_CARRO_HORA,
				VALOR_CARRO_DIA, HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(8000, resultado);
	}

	@Test
	public void cobrarRetiroCarroDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_CARRO_HORA,
				VALOR_CARRO_DIA, HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(11000, resultado);
	}

	@Test
	public void cobrarRetiroCarroMenosDeUnaHoraTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 15, 6);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_CARRO_HORA,
				VALOR_CARRO_DIA, HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(1000, resultado);
	}

	@Test
	public void cobrarRetiroMotoMenosDeNueveHorasTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 18, 5);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_MOTO_HORA, VALOR_MOTO_DIA,
				HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(1500, resultado);
	}

	@Test
	public void cobrarRetiroMotoDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_MOTO_HORA, VALOR_MOTO_DIA,
				HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(4000, resultado);
	}

	@Test
	public void cobrarRetiroMotoDeMasDeUnDiaTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 12, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 16, 5);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_MOTO_HORA, VALOR_MOTO_DIA,
				HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(5500, resultado);
	}

	@Test
	public void CobrarRetiroMotoMenosDeUnaHoraTest() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 15, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 15, 6);

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_MOTO_HORA, VALOR_MOTO_DIA,
				HORAS_MINIMAS_COBRO_HORA, 0, 0);

		// Assert
		assertEquals(500, resultado);
	}

	@Test
	public void cobrarRetiroMotoDeUnDiaYCilindrajeMayorA500Test() {
		// Arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2018, 5, 13, 13, 5);
		LocalDateTime fechaRetiro = LocalDateTime.of(2018, 5, 13, 23, 5);

		MotoDTO moto = aMotoDTO().withCilindraje(650).build();

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);
		celdaParqueaderoDTO.setMoto(moto);

		// Act
		int resultado = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, VALOR_MOTO_HORA, VALOR_MOTO_DIA,
				HORAS_MINIMAS_COBRO_HORA, CILINDRAJE_MAXIMO, IMPUESTO_CILINDRAJE_MAXIMO);

		// Assert
		assertEquals(6000, resultado);
	}
}
