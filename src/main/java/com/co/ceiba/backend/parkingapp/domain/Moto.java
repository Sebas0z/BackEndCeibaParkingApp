package com.co.ceiba.backend.parkingapp.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Moto extends Vehiculo {

	@NotNull
	private int cilindraje;

	protected Moto() {
	}

	public Moto(String placa, int cilindraje) {
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
