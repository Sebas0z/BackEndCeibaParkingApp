package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoMotoRepository;

@Service("parqueaderoMotoService")
public class ParqueaderoMotoServiceImpl implements ParqueaderoMotoService {

	@Autowired
	ParqueaderoMotoRepository parqueaderoMotoRepository;

	@Override
	public ParqueaderoMoto guardarParqueaderoMoto(ParqueaderoMoto parqueaderoMoto) {
		return parqueaderoMotoRepository.save(parqueaderoMoto);
	}

	@Override
	public List<ParqueaderoMoto> obtenerMotosParqueadas() {
		return parqueaderoMotoRepository.findByFechaRetiroIsNull();
	}

	@Override
	public ParqueaderoMoto obtenerMotoParqueada(Moto moto) {
		return parqueaderoMotoRepository.findByMotoAndFechaRetiroIsNull(moto);
	}

}
