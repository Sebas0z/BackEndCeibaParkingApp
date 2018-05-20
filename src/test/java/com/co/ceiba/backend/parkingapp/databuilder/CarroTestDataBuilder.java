package com.co.ceiba.backend.parkingapp.databuilder;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;

public class CarroTestDataBuilder {
	
	private String placa;
	
	public CarroTestDataBuilder() {
		placa = "COT18S";
	}
	
	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public CarroDTO build() {
		return new CarroDTO(placa);
	}
	
	public static CarroTestDataBuilder aCarro() {
		return new CarroTestDataBuilder();
	}
	
}
