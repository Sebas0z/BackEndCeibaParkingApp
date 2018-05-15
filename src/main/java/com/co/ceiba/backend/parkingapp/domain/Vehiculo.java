package com.co.ceiba.backend.parkingapp.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Vehiculo {

	private String placa;

	protected Vehiculo() {
	}

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
