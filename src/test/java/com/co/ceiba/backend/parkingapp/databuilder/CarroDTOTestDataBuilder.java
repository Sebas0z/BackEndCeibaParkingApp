package com.co.ceiba.backend.parkingapp.databuilder;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;

public class CarroDTOTestDataBuilder {
	
	private String placa;
	
	public CarroDTOTestDataBuilder() {
		placa = "COT18S";
	}
	
	public CarroDTOTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public CarroDTO build() {
		return new CarroDTO(placa);
	}
	
	public static CarroDTOTestDataBuilder aCarroDTO() {
		return new CarroDTOTestDataBuilder();
	}
	
}
