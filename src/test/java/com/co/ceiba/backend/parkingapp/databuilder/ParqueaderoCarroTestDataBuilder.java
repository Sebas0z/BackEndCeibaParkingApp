package com.co.ceiba.backend.parkingapp.databuilder;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;

public class ParqueaderoCarroTestDataBuilder {

	private CarroDTO carro;
	private LocalDateTime fechaIngreso;

	private ModelMapper modelMapper = new ModelMapper();

	public ParqueaderoCarroTestDataBuilder() {
		fechaIngreso = LocalDateTime.now();
	}

	public ParqueaderoCarroTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public ParqueaderoCarroTestDataBuilder withCarro(CarroDTO carro) {
		this.carro = carro;
		return this;
	}

	public ParqueaderoCarro build() {
		return new ParqueaderoCarro(modelMapper.map(carro, Carro.class), fechaIngreso);
	}

	public static ParqueaderoCarroTestDataBuilder aParqueaderoCarro() {
		return new ParqueaderoCarroTestDataBuilder();
	}

}
