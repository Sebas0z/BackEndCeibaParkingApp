package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

public class ParqueoMoto extends Parqueo {

	private Moto moto;

	public ParqueoMoto(Moto moto, LocalDateTime fechaIngreso) {
		super(fechaIngreso);
		this.moto = moto;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

}
