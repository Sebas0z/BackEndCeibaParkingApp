package com.co.ceiba.backend.parkingapp.databuilder;

import java.time.LocalDateTime;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueoMoto;

public class ParqueoMotoTestDataBuilder {

	private Moto moto;
	private LocalDateTime fechaIngreso;

	public ParqueoMotoTestDataBuilder() {
		fechaIngreso = LocalDateTime.now();
	}

	public ParqueoMotoTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public ParqueoMotoTestDataBuilder withMoto(Moto moto) {
		this.moto = moto;
		return this;
	}

	public ParqueoMoto build() {
		return new ParqueoMoto(moto, fechaIngreso);
	}

	public static ParqueoMotoTestDataBuilder aParqueoMoto() {
		return new ParqueoMotoTestDataBuilder();
	}

}
