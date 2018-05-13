package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

public abstract class Parqueadero {

	private LocalDateTime fechaIngreso;

	public Parqueadero(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
