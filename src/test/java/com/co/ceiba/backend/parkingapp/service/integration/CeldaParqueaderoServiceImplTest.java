package com.co.ceiba.backend.parkingapp.service.integration;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroDTOTestDataBuilder.aCarroDTO;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoDTOTestDataBuilder.aMotoDTO;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.CeldaParqueaderoService;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CeldaParqueaderoServiceImplTest {

	@Autowired
	private CeldaParqueaderoService celdaParqueaderoService;

	@Autowired
	private VehiculoService<CarroDTO> carroService;

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@Test
	public void Test1GuardarCeldaParqueaderoCarroTest() {
		// Arrange
		CarroDTO carroDTO = carroService.guardarVehiculo(aCarroDTO().build());
		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
		celdaParqueaderoDTO.setCarro(carroDTO);

		// Act
		CeldaParqueaderoDTO resultado = celdaParqueaderoService.guardarCeldaParqueadero(celdaParqueaderoDTO);

		// Assert
		assertEquals(carroDTO.getPlaca(), resultado.getCarro().getPlaca());
		assertEquals(celdaParqueaderoDTO.getFechaIngreso(), resultado.getFechaIngreso());
	}

	@Test
	public void Test2GuardarCeldaParqueaderoMotoTest() {
		// Arrange
		MotoDTO motoDTO = motoService.guardarVehiculo(aMotoDTO().build());
		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(LocalDateTime.now());
		celdaParqueaderoDTO.setMoto(motoDTO);

		// Act
		CeldaParqueaderoDTO resultado = celdaParqueaderoService.guardarCeldaParqueadero(celdaParqueaderoDTO);

		// Assert
		assertEquals(motoDTO.getPlaca(), resultado.getMoto().getPlaca());
		assertEquals(motoDTO.getCilindraje(), resultado.getMoto().getCilindraje());
		assertEquals(celdaParqueaderoDTO.getFechaIngreso(), resultado.getFechaIngreso());
	}

	@Test
	public void Test3ObtenerVehiculosParqueadosTest() {
		// Act
		List<CeldaParqueaderoDTO> resultado = celdaParqueaderoService.buscarVehiculosParqueados();

		// Assert
		assertEquals(2, resultado.size());
	}

}
