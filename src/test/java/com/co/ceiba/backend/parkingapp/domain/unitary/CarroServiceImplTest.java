package com.co.ceiba.backend.parkingapp.domain.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
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
import com.co.ceiba.backend.parkingapp.reposity.CarroRepository;
import com.co.ceiba.backend.parkingapp.service.CarroService;
import com.co.ceiba.backend.parkingapp.service.CarroServiceImpl;

@RunWith(SpringRunner.class)
public class CarroServiceImplTest {

	@TestConfiguration
	static class CarroServiceImplTestContextConfiguration {

		@Bean
		public CarroService getCarroService() {
			return new CarroServiceImpl();
		}
	}

	@Autowired
	private CarroService carroService;

	@MockBean
	private CarroRepository CarroRepository;

	@Test
	public void agregarCarroTest() {
		// Arrange
		Carro carro = aCarro().build();
		Mockito.when(CarroRepository.save(carro)).thenReturn(carro);

		// Act
		Carro carroAgregado = carroService.agregarCarro(carro);

		// Assert
		assertEquals(carro.getPlaca(), carroAgregado.getPlaca());
	}

	@Test
	public void obtenerCarro() {
		// Arrange
		Carro carro = aCarro().build();
		Mockito.when(carroService.obtenerCarro(carro.getPlaca())).thenReturn(carro);

		// Act
		Carro carroObtenido = carroService.obtenerCarro(carro.getPlaca());

		// Assert
		assertEquals(carro.getPlaca(), carroObtenido.getPlaca());

	}

}
