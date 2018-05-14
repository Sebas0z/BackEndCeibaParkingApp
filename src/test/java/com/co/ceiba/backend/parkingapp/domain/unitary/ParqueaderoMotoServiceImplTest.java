package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

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

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoMotoRepository;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoMotoService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoMotoServiceImpl;

@RunWith(SpringRunner.class)
public class ParqueaderoMotoServiceImplTest {

	@TestConfiguration
	static class ParqueaderoMotoServiceImplTestContextConfiguration {

		@Bean
		public ParqueaderoMotoService getParqueaderoMotoService() {
			return new ParqueaderoMotoServiceImpl();
		}
	}

	@Autowired
	ParqueaderoMotoService parqueaderoMotoService;

	@MockBean
	ParqueaderoMotoRepository parqueaderoMotoRepository;

	@Test
	public void agregarParqueaderoMotoTest() {
		// Arrange
		Moto moto = aMoto().build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();
		Mockito.when(parqueaderoMotoRepository.save(parqueaderoMoto)).thenReturn(parqueaderoMoto);

		// Act
		ParqueaderoMoto parqueaderoMotoAgregado = parqueaderoMotoService.agregarParqueaderoMoto(parqueaderoMoto);

		// Assert
		assertEquals(parqueaderoMoto.getMoto().getPlaca(), parqueaderoMotoAgregado.getMoto().getPlaca());

	}

	@Test
	public void obtenerMotosParqueadasTest() {
		// Arrange
		Moto moto = aMoto().build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();
		List<ParqueaderoMoto> listParqueaderoMoto = new ArrayList<>();
		listParqueaderoMoto.add(parqueaderoMoto);

		Mockito.when(parqueaderoMotoRepository.findByFechaRetiroIsNull()).thenReturn(listParqueaderoMoto);

		// Act
		List<ParqueaderoMoto> parqueaderoMotoObtenidos = parqueaderoMotoService.obtenerMotosParqueadas();

		// Assert
		assertEquals(1, parqueaderoMotoObtenidos.size());
	}

	@Test
	public void obtenerCMotoParqueadoTest() {
		// Arrange
		Moto moto = aMoto().build();
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();
		Mockito.when(parqueaderoMotoRepository.findByMotoAndFechaRetiroIsNull(moto)).thenReturn(parqueaderoMoto);

		// Act
		ParqueaderoMoto parqueaderoMotoObtenido = parqueaderoMotoService.obtenerMotoParqueada(moto);

		// Assert
		assertEquals(moto.getPlaca(), parqueaderoMotoObtenido.getMoto().getPlaca());
	}

}
