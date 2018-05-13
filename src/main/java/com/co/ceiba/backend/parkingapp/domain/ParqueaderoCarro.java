package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

public class ParqueoCarro extends Parqueo {

	private Carro carro;

	public ParqueoCarro(Carro carro, LocalDateTime fechaIngreso) {
		super(fechaIngreso);
		this.carro = carro;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro vehiculo) {
		this.carro = vehiculo;
	}

}
