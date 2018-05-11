package com.co.ceiba.backend.parkingapp.domain;

public abstract class Vehiculo {
	
	private String placa;

	public Vehiculo(String placa) {
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
