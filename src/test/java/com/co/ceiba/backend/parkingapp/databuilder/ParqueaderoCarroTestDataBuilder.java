package com.co.ceiba.backend.parkingapp.databuilder;

import java.time.LocalDateTime;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueoCarro;

public class ParqueoCarroTestDataBuilder {
	
	private Carro carro;
	private LocalDateTime fechaIngreso;
	
	public ParqueoCarroTestDataBuilder() {
		fechaIngreso = LocalDateTime.now();
	}
	
	public ParqueoCarroTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public ParqueoCarroTestDataBuilder withCarro(Carro carro) {
		this.carro = carro;
		return this;
	}
	
	public ParqueoCarro build() {
		return new ParqueoCarro(carro, fechaIngreso);
	}
	
	public static ParqueoCarroTestDataBuilder aParqueoCarro() {
		return new ParqueoCarroTestDataBuilder();
	}

}
