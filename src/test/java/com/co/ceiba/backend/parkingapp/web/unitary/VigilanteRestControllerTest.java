package com.co.ceiba.backend.parkingapp.web.unitary;

import static com.co.ceiba.backend.parkingapp.databuilder.CarroDTOTestDataBuilder.aCarroDTO;
import static com.co.ceiba.backend.parkingapp.databuilder.MotoDTOTestDataBuilder.aMotoDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.service.VigilanteService;
import com.co.ceiba.backend.parkingapp.web.VigilanteRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(VigilanteRestController.class)
public class VigilanteRestControllerTest {

	private static final String PLACA = "placa";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VigilanteService vigilanteService;

	@Test
	public void ingresarCarro() throws Exception {
		// Arrange
		CarroDTO carro = aCarroDTO().build();
		Mockito.when(vigilanteService.registrarIngresoVehiculo(Mockito.anyString(), Mockito.anyInt(), Mockito.any()))
				.thenReturn("Carro registrado en el parqueadero");

		// Act & Assert
		mockMvc.perform(get("/vigilante/registrarIngresoVehiculo").param(PLACA, carro.getPlaca())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string("Carro registrado en el parqueadero"));
	}

	@Test
	public void ingresarMoto() throws Exception {
		// Arrange
		MotoDTO moto = aMotoDTO().build();
		Mockito.when(vigilanteService.registrarIngresoVehiculo(Mockito.anyString(), Mockito.anyInt(), Mockito.any()))
				.thenReturn("Moto registrada en el parqueadero");

		// Act & Assert
		mockMvc.perform(get("/vigilante/registrarIngresoVehiculo").param(PLACA, moto.getPlaca()).param("cilindraje",
				Integer.toString(moto.getCilindraje()))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Moto registrada en el parqueadero"));
	}

	@Test
	public void cobrarRetiroVehiculo() throws Exception {
		// Arrange
		CarroDTO carro = aCarroDTO().build();
		Mockito.when(vigilanteService.cobrarRetiroVehiculo(Mockito.anyString(), Mockito.any())).thenReturn("11000");

		// Act & Assert
		mockMvc.perform(get("/vigilante/cobrarRetiroVehiculo").param(PLACA, carro.getPlaca())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string("11000"));
	}

}
