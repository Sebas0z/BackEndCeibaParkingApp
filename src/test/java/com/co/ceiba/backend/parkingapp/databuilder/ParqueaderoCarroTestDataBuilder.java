package com.co.ceiba.backend.parkingapp.databuilder;

import java.time.LocalDateTime;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;

public class ParqueaderoCarroTestDataBuilder {

	private Carro carro;
	private LocalDateTime fechaIngreso;

	public ParqueaderoCarroTestDataBuilder() {
		fechaIngreso = LocalDateTime.now();
	}

	public ParqueaderoCarroTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public ParqueaderoCarroTestDataBuilder withCarro(Carro carro) {
		this.carro = carro;
		return this;
	}

	public ParqueaderoCarro build() {
		return new ParqueaderoCarro(carro, fechaIngreso);
	}

	public static ParqueaderoCarroTestDataBuilder aParqueaderoCarro() {
		return new ParqueaderoCarroTestDataBuilder();
	}

}
