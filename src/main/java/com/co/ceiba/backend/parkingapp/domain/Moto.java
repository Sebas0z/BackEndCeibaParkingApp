package com.co.ceiba.backend.parkingapp.domain;

public class Moto {

	private String placa;
	private int cilindraje;

	public Moto(String placa, int cilindraje) {
		this.placa = placa;
		this.cilindraje = cilindraje;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

}
