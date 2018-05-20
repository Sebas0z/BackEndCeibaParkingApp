package com.co.ceiba.backend.parkingapp.dto;

public class MotoDTO extends VehiculoDTO {

	private int cilindraje;
	
	protected MotoDTO() {
	}

	public MotoDTO(String placa, int cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

}
