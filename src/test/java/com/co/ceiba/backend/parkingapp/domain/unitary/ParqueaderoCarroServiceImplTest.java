package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoCarroRepository;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroServiceImpl;

@RunWith(SpringRunner.class)
public class ParqueaderoCarroServiceImplTest {

	@TestConfiguration
	static class ParqueaderoCarroServiceImplTestContextConfiguration {

		@Bean
		public ParqueaderoCarroService parqueaderoCarroService() {
			return new ParqueaderoCarroServiceImpl();
		}
	}

	@Autowired
	ParqueaderoCarroService parqueaderoCarroService;

	@MockBean
	ParqueaderoCarroRepository parqueaderoCarroRepository;

	@Test
	public void agregarParqueaderoCarroTest() {
		// Arrange
		Carro carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
		Mockito.when(parqueaderoCarroRepository.save(parqueaderoCarro)).thenReturn(parqueaderoCarro);

		// Act
		ParqueaderoCarro parqueaderoCarroAgregado = parqueaderoCarroService.agregarParqueaderoCarro(parqueaderoCarro);
		// Assert
		assertEquals(parqueaderoCarro.getCarro().getPlaca(), parqueaderoCarroAgregado.getCarro().getPlaca());
		assertEquals(parqueaderoCarro.getFechaIngreso(), parqueaderoCarroAgregado.getFechaIngreso());
	}

}
