package com.co.ceiba.backend.parkingapp.databuilder;

import com.co.ceiba.backend.parkingapp.dto.MotoDTO;

public class MotoDTOTestDataBuilder {

	private String placa;
	private int cilindraje;

	public MotoDTOTestDataBuilder() {
		placa = "HUV11P";
		cilindraje = 150;
	}

	public MotoDTOTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MotoDTOTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public MotoDTO build() {
		return new MotoDTO(placa, cilindraje);
	}

	public static MotoDTOTestDataBuilder aMotoDTO() {
		return new MotoDTOTestDataBuilder();
	}

}
