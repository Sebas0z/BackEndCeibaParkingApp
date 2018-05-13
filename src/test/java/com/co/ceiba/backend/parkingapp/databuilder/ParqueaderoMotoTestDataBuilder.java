package com.co.ceiba.backend.parkingapp.databuilder;

import java.time.LocalDateTime;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public class ParqueaderoMotoTestDataBuilder {

	private Moto moto;
	private LocalDateTime fechaIngreso;

	public ParqueaderoMotoTestDataBuilder() {
		fechaIngreso = LocalDateTime.now();
	}

	public ParqueaderoMotoTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public ParqueaderoMotoTestDataBuilder withMoto(Moto moto) {
		this.moto = moto;
		return this;
	}

	public ParqueaderoMoto build() {
		return new ParqueaderoMoto(moto, fechaIngreso);
	}

	public static ParqueaderoMotoTestDataBuilder aParqueaderoMoto() {
		return new ParqueaderoMotoTestDataBuilder();
	}

}
