package com.co.ceiba.backend.parkingapp.databuilder;

import com.co.ceiba.backend.parkingapp.domain.Moto;

public class MotoTestDataBuilder {
	
	private String placa;
	private int cilindraje;
	
	public MotoTestDataBuilder() {
		placa = "HUV11P";
		cilindraje = 150;
	}
	
	public MotoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Moto build() {
		return new Moto(placa, cilindraje);
	}
	
	public static MotoTestDataBuilder anMoto() {
		return new MotoTestDataBuilder();
	}
	
	

}
