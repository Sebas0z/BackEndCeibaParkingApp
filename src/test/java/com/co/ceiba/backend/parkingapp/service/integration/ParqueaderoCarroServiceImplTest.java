package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoCarroTestDataBuilder.aParqueaderoCarro;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.service.CarroService;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoCarroService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoCarroServiceImplTest {

	@Autowired
	private ParqueaderoCarroService parqueaderoCarroService;

	@Autowired
	private CarroService carroService;

	@Test
	public void Test1GuardarParqueaderoCarroTest() {
		// Arrange
		Carro carro = carroService.guardarCarro(aCarro().build());
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();

		// Act
		ParqueaderoCarro parqueaderoCarroAgregado = parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro);
		// Assert
		assertEquals(parqueaderoCarro.getCarro().getPlaca(), parqueaderoCarroAgregado.getCarro().getPlaca());
		assertEquals(parqueaderoCarro.getFechaIngreso(), parqueaderoCarroAgregado.getFechaIngreso());
	}

	@Test
	public void Test2ObtenerCarrosParqueadosTest() {
		// Arrange
		Carro carro = carroService.guardarCarro(aCarro().build());
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
		parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro);

		ParqueaderoCarro parqueaderoCarro2 = aParqueaderoCarro().withCarro(carro).build();
		parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro2);

		// Act
		List<ParqueaderoCarro> parqueaderoCarroObtenidos = parqueaderoCarroService.obtenerCarrosParqueados();

		// Assert
		assertEquals(3, parqueaderoCarroObtenidos.size());
	}

	@Test
	public void Test3ObtenerCarroParqueadoTest() {
		// Arrange
		Carro carro = carroService.guardarCarro(aCarro().build());
		ParqueaderoCarro parqueaderoCarro = aParqueaderoCarro().withCarro(carro).build();
		parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro);

		// Act
		ParqueaderoCarro parqueaderoCarroObtenido = parqueaderoCarroService.obtenerCarroParqueado(carro);

		// Assert
		assertEquals(carro.getPlaca(), parqueaderoCarroObtenido.getCarro().getPlaca());
	}

}
