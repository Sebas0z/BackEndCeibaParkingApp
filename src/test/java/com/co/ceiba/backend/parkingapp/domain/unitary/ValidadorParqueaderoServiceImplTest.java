package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.service.ValidadorParqueaderoService;
import com.co.ceiba.backend.parkingapp.service.ValidadorParqueaderoServiceImpl;

@RunWith(SpringRunner.class)
public class ValidadorParqueaderoServiceImplTest {

	private static final String PLACA_SIN_INICIAL_A = "BOS16X";
	private static final String PLACA_CON_INICIAL_A = "AOZ15D";

	private static final int MAXIMA_CANTIDAD_CARROS = 20;
	private static final int MAXIMA_CANTIDAD_MOTOS = 10;

	private static final LocalDateTime LUNES = LocalDateTime.of(2018, 5, 14, 10, 10);
	private static final LocalDateTime DOMINGO = LocalDateTime.of(2018, 5, 13, 10, 10);
	private static final LocalDateTime SABADO = LocalDateTime.of(2018, 5, 12, 10, 10);

	@TestConfiguration
	static class ValidadorParqueaderoServiceImplTestContextConfiguration {

		@Bean
		public ValidadorParqueaderoService getValidadorParqueaderoService() {
			return new ValidadorParqueaderoServiceImpl();
		}
	}

	@Autowired
	private ValidadorParqueaderoService validadorParqueaderoService;

	@Test
	public void validarSiHayEspacioParaCarroTest() {
		// Arrange
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(aCarro().build()).build();

		// Act
		boolean menosDeVeinteCarros = validadorParqueaderoService.validarSiHayEspacioParaCarro(parqueaderoCarro);

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
		boolean menosDeVeinteCarros = validadorParqueaderoService
				.validarSiHayEspacioParaCarro(arregloParqueaderoCarros);

		// Assert
		assertFalse(menosDeVeinteCarros);
	}

	@Test
	public void validarSiHayEspacioParaMotoTest() {
		// Arrange
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(aMoto().build()).build();

		// Act
		boolean menosDeDiesMotos = validadorParqueaderoService.validarSiHayEspacioParaMoto(parqueaderoMoto);

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
		boolean menosDeDiesMotos = validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMotos);

		// Assert
		assertFalse(menosDeDiesMotos);
	}

	@Test
	public void validarSiPlacaIniciaConATest() {
		// Arrange & Act
		boolean placaIniciaConA = validadorParqueaderoService.validarCondicionPlaca(PLACA_CON_INICIAL_A);

		// Assert
		assertTrue(placaIniciaConA);
	}

	@Test
	public void validarSiPlacaIniciaSinATest() {
		// Arrange & Act
		boolean placaIniciaSinA = validadorParqueaderoService.validarCondicionPlaca(PLACA_SIN_INICIAL_A);

		// Assert
		assertFalse(placaIniciaSinA);
	}

	@Test
	public void validarSiDiaEsLunesTest() {
		// Arrange & Act
		boolean esLunes = validadorParqueaderoService.validarCondicionDia(LUNES);

		// Assert
		assertTrue(esLunes);
	}

	@Test
	public void validarSiDiaEsDomingoTest() {
		// Arrange & Act
		boolean esDomingo = validadorParqueaderoService.validarCondicionDia(DOMINGO);

		// Assert
		assertTrue(esDomingo);
	}

	@Test
	public void validarSiDiaNoEsLunesNiDomingoTest() {
		// Arrange & Act
		boolean noEsDomingo = validadorParqueaderoService.validarCondicionDia(SABADO);

		// Assert
		assertFalse(noEsDomingo);
	}

}
