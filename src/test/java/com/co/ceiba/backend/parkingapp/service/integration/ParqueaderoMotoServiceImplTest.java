package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
import static com.co.ceiba.backend.parkingapp.databuilder.ParqueaderoMotoTestDataBuilder.aParqueaderoMoto;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.ParqueaderoMotoService;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoMotoServiceImplTest {

	@Autowired
	private ParqueaderoMotoService parqueaderoMotoService;

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@Test
	public void Test1GuardarParqueaderoMotoTest() {
		// Arrange
		MotoDTO moto = motoService.guardarVehiculo(aMoto().build());
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();

		// Act
		ParqueaderoMoto parqueaderoMotoAgregado = parqueaderoMotoService.guardarParqueaderoMoto(parqueaderoMoto);

		// Assert
		assertEquals(parqueaderoMoto.getMoto().getPlaca(), parqueaderoMotoAgregado.getMoto().getPlaca());

	}

	@Test
	public void Test2ObtenerMotosParqueadasTest() {
		// Arrange
		MotoDTO moto = motoService.guardarVehiculo(aMoto().build());
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();

		parqueaderoMotoService.guardarParqueaderoMoto(parqueaderoMoto);

		ParqueaderoMoto parqueaderoMoto2 = aParqueaderoMoto().withMoto(moto).build();
		parqueaderoMotoService.guardarParqueaderoMoto(parqueaderoMoto2);

		// Act
		List<ParqueaderoMoto> parqueaderoMotoObtenidos = parqueaderoMotoService.obtenerMotosParqueadas();

		// Assert
		assertEquals(3, parqueaderoMotoObtenidos.size());
	}

	@Test
	public void Test3ObtenerMotoParqueadoTest() {
		// Arrange
		MotoDTO moto = motoService.guardarVehiculo(aMoto().build());
		ParqueaderoMoto parqueaderoMoto = aParqueaderoMoto().withMoto(moto).build();
		parqueaderoMotoService.guardarParqueaderoMoto(parqueaderoMoto);

		// Act
		ParqueaderoMoto parqueaderoMotoObtenido = parqueaderoMotoService.obtenerMotoParqueada(moto);

		// Assert
		assertEquals(moto.getPlaca(), parqueaderoMotoObtenido.getMoto().getPlaca());
	}

}
