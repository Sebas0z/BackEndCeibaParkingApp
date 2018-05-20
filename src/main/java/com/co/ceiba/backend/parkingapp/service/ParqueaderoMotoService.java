package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;

public interface ParqueaderoMotoService {

	public ParqueaderoMoto guardarParqueaderoMoto(ParqueaderoMoto parqueaderoMoto);

	public List<ParqueaderoMoto> obtenerMotosParqueadas();

	public ParqueaderoMoto obtenerMotoParqueada(MotoDTO moto);
}
