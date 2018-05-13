package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

public abstract class Parqueo {

	private LocalDateTime fechaIngreso;

	public Parqueo(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
