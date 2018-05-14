package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public interface ParqueaderoMotoService {

	public ParqueaderoMoto agregarParqueaderoMoto(ParqueaderoMoto parqueaderoMoto);

	public List<ParqueaderoMoto> obtenerMotosParqueadas();

	public ParqueaderoMoto obtenerMotoParqueada(Moto moto);
}
