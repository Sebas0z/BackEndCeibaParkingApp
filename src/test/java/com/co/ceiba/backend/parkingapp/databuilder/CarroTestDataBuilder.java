package com.co.ceiba.backend.parkingapp.databuilder;

import com.co.ceiba.backend.parkingapp.domain.Carro;

public class CarroTestDataBuilder {
	
	private String placa;
	
	public CarroTestDataBuilder() {
		placa = "COT18S";
	}
	
	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public Carro build() {
		return new Carro(placa);
	}
	
	public static CarroTestDataBuilder aCarro() {
		return new CarroTestDataBuilder();
	}
	
}
