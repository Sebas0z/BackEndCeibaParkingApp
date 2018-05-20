package com.co.ceiba.backend.parkingapp.databuilder;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;

public class ParqueaderoMotoTestDataBuilder {

	private MotoDTO moto;
	private LocalDateTime fechaIngreso;

	private ModelMapper modelMapper = new ModelMapper();

	public ParqueaderoMotoTestDataBuilder() {
		fechaIngreso = LocalDateTime.now();
	}

	public ParqueaderoMotoTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public ParqueaderoMotoTestDataBuilder withMoto(MotoDTO moto) {
		this.moto = moto;
		return this;
	}

	public ParqueaderoMoto build() {
		return new ParqueaderoMoto(modelMapper.map(moto, Moto.class), fechaIngreso);
	}

	public static ParqueaderoMotoTestDataBuilder aParqueaderoMoto() {
		return new ParqueaderoMotoTestDataBuilder();
	}

}
