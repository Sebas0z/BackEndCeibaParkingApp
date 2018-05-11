package com.co.ceiba.backend.parkingapp.domain.unitary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.anCarro;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CarroTest {
	
	@Test
	public void crearCarroTest() {
		
		// Arrange & Act
		Carro carro = anCarro().withPlaca("ASD10D").build();
		// Assert
		assertEquals("ASD10D", carro.getPlaca());
		
	}

}
