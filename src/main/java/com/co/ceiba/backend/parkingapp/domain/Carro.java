package com.co.ceiba.backend.parkingapp.domain;

import javax.persistence.Entity;

@Entity
public class Carro extends Vehiculo {

	protected Carro() {
	}

	public Carro(String placa) {
		super(placa);
	}

}
