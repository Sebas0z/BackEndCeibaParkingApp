package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

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
		public ParqueaderoMotoService parqueaderoMotoService() {
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

		// Asser
		assertEquals(parqueaderoMoto.getMoto().getPlaca(), parqueaderoMotoAgregado.getMoto().getPlaca());

	}

}
