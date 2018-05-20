package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.reposity.CarroRepository;
import com.co.ceiba.backend.parkingapp.service.CarroServiceImpl;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;

@RunWith(SpringRunner.class)
public class CarroServiceImplTest {

	@TestConfiguration
	static class CarroServiceImplTestContextConfiguration {

		@Bean
		public VehiculoService<CarroDTO> getCarroService() {
			return new CarroServiceImpl();
		}
	}

	@Autowired
	private VehiculoService<CarroDTO> carroService;

	@MockBean
	private CarroRepository carroRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void guardarCarroTest() {
		// Arrange
		CarroDTO carro = aCarro().build();

		// Act
		Mockito.when(carroRepository.save(Mockito.any())).thenReturn(modelMapper.map(carro, Carro.class));
		CarroDTO carroAgregado = carroService.guardarVehiculo(carro);

		// Assert
		assertEquals(carro.getPlaca(), carroAgregado.getPlaca());
	}

	@Test
	public void obtenerCarro() {
		// Arrange
		CarroDTO carro = aCarro().build();

		// Act
		Mockito.when(carroRepository.findByPlaca(carro.getPlaca())).thenReturn(modelMapper.map(carro, Carro.class));
		CarroDTO carroObtenido = carroService.buscarVehiculoPorPlaca(carro.getPlaca());

		// Assert
		assertEquals(carro.getPlaca(), carroObtenido.getPlaca());

	}

}
