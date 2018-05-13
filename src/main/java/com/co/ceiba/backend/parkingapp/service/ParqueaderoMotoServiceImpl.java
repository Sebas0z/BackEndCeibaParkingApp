package com.co.ceiba.backend.parkingapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoMotoRepository;

public class ParqueaderoMotoServiceImpl implements ParqueaderoMotoService {
	
	@Autowired
	ParqueaderoMotoRepository parqueaderoMotoRepository;

	@Override
	public ParqueaderoMoto agregarParqueaderoMoto(ParqueaderoMoto parqueaderoMoto) {
		return parqueaderoMotoRepository.save(parqueaderoMoto);
	}

}
