package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

public abstract class Parqueadero {

	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaRetiro;

	public Parqueadero(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

}
