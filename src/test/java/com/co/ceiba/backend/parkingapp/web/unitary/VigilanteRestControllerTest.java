package com.co.ceiba.backend.parkingapp.web.unitary;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.co.ceiba.backend.parkingapp.service.VigilanteService;
import com.co.ceiba.backend.parkingapp.service.VigilanteServiceImpl;
import com.co.ceiba.backend.parkingapp.web.VigilanteRestController;


@RunWith(SpringRunner.class)
@WebMvcTest(VigilanteRestController.class)
public class VigilanteRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VigilanteService vigilanteService;

	@Test
	public void ingresarCarroTest() throws Exception {
		Mockito.when(vigilanteService.cobrarRetiroCarro("SOS15S", LocalDateTime.now())).thenReturn("hola");

		mockMvc.perform(MockMvcRequestBuilders.get("/vigilante/hola").param("msg", "SOS15S")).andDo(print())
				.andExpect(status().isOk());
		// .andExpect(content().string(containsString("Carro registrado en el
		// parqueadero")));
		// .andExpect(content().string(containsString("Carro registrado en el
		// parqueadero")));
	}

}
