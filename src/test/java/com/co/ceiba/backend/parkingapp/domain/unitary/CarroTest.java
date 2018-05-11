package com.co.ceiba.backend.parkingapp.domain.unitary;

import static org.junit.Assert.assertEquals;
import static com.co.ceiba.backend.parkingapp.databuilder.CarroTestDataBuilder.aCarro;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.backend.parkingapp.domain.Carro;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CarroTest {
	
	@Test
	public void crearVehiculoTest() {
		
		// Arrange & Act
		Carro carro = aCarro().withPlaca("ASD10D").build();
		// Assert
		assertEquals("ASD10D", carro.getPlaca());
		
	}

}
