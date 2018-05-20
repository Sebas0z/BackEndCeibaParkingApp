package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
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

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoCarroRepository;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroServiceImpl;

@RunWith(SpringRunner.class)
public class ParqueaderoCarroServiceImplTest {

	@TestConfiguration
	static class ParqueaderoCarroServiceImplTestContextConfiguration {

		@Bean
		public ParqueaderoCarroService getParqueaderoCarroService() {
			return new ParqueaderoCarroServiceImpl();
		}
	}

	@Autowired
	private ParqueaderoCarroService parqueaderoCarroService;

	@MockBean
	private ParqueaderoCarroRepository parqueaderoCarroRepository;

	@Test
	public void guardarParqueaderoCarroTest() {
		// Arrange
		CarroDTO carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
		Mockito.when(parqueaderoCarroRepository.save(parqueaderoCarro)).thenReturn(parqueaderoCarro);

		// Act
		ParqueaderoCarro parqueaderoCarroAgregado = parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro);
		// Assert
		assertEquals(parqueaderoCarro.getCarro().getPlaca(), parqueaderoCarroAgregado.getCarro().getPlaca());
		assertEquals(parqueaderoCarro.getFechaIngreso(), parqueaderoCarroAgregado.getFechaIngreso());
	}

	@Test
	public void obtenerCarrosParqueadosTest() {
		// Arrange
		CarroDTO carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
		List<ParqueaderoCarro> listaParqueaderoCarro = new ArrayList<>();
		listaParqueaderoCarro.add(parqueaderoCarro);

		Mockito.when(parqueaderoCarroRepository.findByFechaRetiroIsNull()).thenReturn(listaParqueaderoCarro);

		// Act
		List<ParqueaderoCarro> parqueaderoCarroObtenidos = parqueaderoCarroService.obtenerCarrosParqueados();

		// Assert
		assertEquals(1, parqueaderoCarroObtenidos.size());
	}

	@Test
	public void obtenerCarroParqueadoTest() {
		// Arrange
		CarroDTO carro = aCarro().build();
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
		Mockito.when(parqueaderoCarroRepository.findByCarroAndFechaRetiroIsNull(Mockito.any())).thenReturn(parqueaderoCarro);

		// Act
		ParqueaderoCarro parqueaderoCarroObtenido = parqueaderoCarroService.obtenerCarroParqueado(carro);

		// Assert
		assertEquals(carro.getPlaca(), parqueaderoCarroObtenido.getCarro().getPlaca());
	}

}
