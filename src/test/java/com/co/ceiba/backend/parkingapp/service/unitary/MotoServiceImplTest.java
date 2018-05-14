package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
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
import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.reposity.MotoRepository;
import com.co.ceiba.backend.parkingapp.service.MotoService;
import com.co.ceiba.backend.parkingapp.service.MotoServiceImpl;

@RunWith(SpringRunner.class)
public class MotoServiceImplTest {

	@TestConfiguration
	static class MotoServiceImplTestContextConfiguration {

		@Bean
		public MotoService getMotoService() {
			return new MotoServiceImpl();
		}
	}

	@Autowired
	MotoService motoService;

	@MockBean
	MotoRepository motoRepository;

	@Test
	public void agregarMotoTest() {
		// Arrange
		Moto moto = aMoto().build();
		Mockito.when(motoRepository.save(moto)).thenReturn(moto);

		// Act
		Moto motoAgregado = motoService.guardarMoto(moto);

		// Assert
		assertEquals(moto.getPlaca(), motoAgregado.getPlaca());
		assertEquals(moto.getCilindraje(), motoAgregado.getCilindraje());

	}

	@Test
	public void obtenerMoto() {
		// Arrange
		Moto moto = aMoto().build();
		Mockito.when(motoService.obtenerMoto(moto.getPlaca())).thenReturn(moto);

		// Act
		Moto carroObtenido = motoService.obtenerMoto(moto.getPlaca());

		// Assert
		assertEquals(moto.getPlaca(), carroObtenido.getPlaca());

	}

}
