package com.co.ceiba.backend.parkingapp.service.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.MotoTestDataBuilder.aMoto;
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

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.reposity.MotoRepository;
import com.co.ceiba.backend.parkingapp.service.MotoServiceImpl;
import com.co.ceiba.backend.parkingapp.service.VehiculoService;

@RunWith(SpringRunner.class)
public class MotoServiceImplTest {

	@TestConfiguration
	static class MotoServiceImplTestContextConfiguration {

		@Bean
		public VehiculoService<MotoDTO> getMotoService() {
			return new MotoServiceImpl();
		}
	}

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@MockBean
	private MotoRepository motoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void guardarMotoTest() {
		// Arrange
		MotoDTO moto = aMoto().build();

		// Act
		Mockito.when(motoRepository.save(Mockito.any())).thenReturn(modelMapper.map(moto, Moto.class));
		MotoDTO motoAgregado = motoService.guardarVehiculo(moto);

		// Assert
		assertEquals(moto.getPlaca(), motoAgregado.getPlaca());
		assertEquals(moto.getCilindraje(), motoAgregado.getCilindraje());

	}

	@Test
	public void obtenerMoto() {
		// Arrange
		MotoDTO moto = aMoto().build();

		// Act
		Mockito.when(motoRepository.findByPlaca(moto.getPlaca())).thenReturn(modelMapper.map(moto, Moto.class));
		MotoDTO motoObtenido = motoService.buscarVehiculoPorPlaca(moto.getPlaca());

		// Assert
		assertEquals(moto.getPlaca(), motoObtenido.getPlaca());

	}

}
