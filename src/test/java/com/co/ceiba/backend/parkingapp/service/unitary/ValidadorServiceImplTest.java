package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroDTOTestDataBuilder.aCarroDTO;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoDTOTestDataBuilder.aMotoDTO;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.ValidadorService;
import com.co.ceiba.backend.parkingapp.service.ValidadorServiceImpl;

@RunWith(SpringRunner.class)
public class ValidadorServiceImplTest {

	private static final String PATRON = "^[A]";
	private static final String PLACA_SIN_INICIAL_A = "BOS16X";
	private static final String PLACA_CON_INICIAL_A = "AOZ15D";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int MAXIMA_CANTIDAD_MOTOS = 10;

	private static final LocalDateTime LUNES = LocalDateTime.of(2018, 5, 14, 10, 10);
	private static final LocalDateTime SABADO = LocalDateTime.of(2018, 5, 12, 10, 10);

	private static List<DayOfWeek> DIAS_SEMANA_HABILES = new ArrayList<>();

	@TestConfiguration
	static class ValidadorServiceImplTestContextConfiguration {

		@Bean
		public ValidadorService getValidadorService() {
			return new ValidadorServiceImpl();
		}
	}

	@Autowired
	private ValidadorService validadorService;

	@Before
	public void setUp() {
		DIAS_SEMANA_HABILES.add(DayOfWeek.MONDAY);
		DIAS_SEMANA_HABILES.add(DayOfWeek.SUNDAY);
	}

	@Test
	public void validarSiHayEspacioParaCarroTest() {
		// Arrange
		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = new ArrayList<>();
		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
		celdaParqueaderoDTO.setCarro(aCarroDTO().build());
		listCeldaParqueaderoDTO.add(celdaParqueaderoDTO);

		// Act
		boolean resultado = validadorService.validarSiHayEspacioParaVehiculo(listCeldaParqueaderoDTO, CarroDTO.class,
				MAXIMA_CANTIDAD_CARROS);

		// Assert
		assertTrue(resultado);
	}

	@Test
	public void validarSiNoHayEspacioParaCarroTest() {
		// Arrange
		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = new ArrayList<>();
		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
		celdaParqueaderoDTO.setCarro(aCarroDTO().build());

		for (int contadorCarros = 1; contadorCarros <= MAXIMA_CANTIDAD_CARROS; contadorCarros++) {
			listCeldaParqueaderoDTO.add(celdaParqueaderoDTO);
		}

		// Act
		boolean resultado = validadorService.validarSiHayEspacioParaVehiculo(listCeldaParqueaderoDTO, CarroDTO.class,
				MAXIMA_CANTIDAD_CARROS);

		// Assert
		assertFalse(resultado);
	}

	@Test
	public void validarSiHayEspacioParaMotoTest() {
		// Arrange
		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = new ArrayList<>();
		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
		celdaParqueaderoDTO.setMoto(aMotoDTO().build());
		listCeldaParqueaderoDTO.add(celdaParqueaderoDTO);

		// Act
		boolean resultado = validadorService.validarSiHayEspacioParaVehiculo(listCeldaParqueaderoDTO, MotoDTO.class,
				MAXIMA_CANTIDAD_MOTOS);

		// Assert
		assertTrue(resultado);
	}

	@Test
	public void validarSiNoHayEspacioParaMotoTest() {
		// Arrange
		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = new ArrayList<>();
		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
		celdaParqueaderoDTO.setMoto(aMotoDTO().build());

		for (int contadorMotos = 1; contadorMotos <= MAXIMA_CANTIDAD_MOTOS; contadorMotos++) {
			listCeldaParqueaderoDTO.add(celdaParqueaderoDTO);
		}

		// Act
		boolean resultado = validadorService.validarSiHayEspacioParaVehiculo(listCeldaParqueaderoDTO, MotoDTO.class,
				MAXIMA_CANTIDAD_MOTOS);

		// Assert
		assertFalse(resultado);
	}

	@Test
	public void validarSiPlacaIniciaConATest() {
		// Arrange & Act
		boolean resultado = validadorService.validarCondicionPlacaSegunPatron(PLACA_CON_INICIAL_A, PATRON);

		// Assert
		assertTrue(resultado);
	}

	@Test
	public void validarSiPlacaIniciaSinATest() {
		// Arrange & Act
		boolean resultado = validadorService.validarCondicionPlacaSegunPatron(PLACA_SIN_INICIAL_A, PATRON);

		// Assert
		assertFalse(resultado);
	}

	@Test
	public void validarSiDiaEsLunesTest() {
		// Act
		boolean resultado = validadorService.validarCondicionFechaSegunDiasDeLaSemana(LUNES, DIAS_SEMANA_HABILES);

		// Assert
		assertTrue(resultado);
	}

	@Test
	public void validarSiDiaNoEsLunesNiDomingoTest() {
		// Act
		boolean resultado = validadorService.validarCondicionFechaSegunDiasDeLaSemana(SABADO, DIAS_SEMANA_HABILES);

		// Assert
		assertFalse(resultado);
	}

}