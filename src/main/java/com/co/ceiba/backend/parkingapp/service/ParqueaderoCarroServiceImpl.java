package com.co.ceiba.backend.parkingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoCarroRepository;

@Service("parqueaderoCarroService")
public class ParqueaderoCarroServiceImpl implements ParqueaderoCarroService {

	@Autowired
	ParqueaderoCarroRepository parqueaderoCarroRepository;

	@Override
	public ParqueaderoCarro agregarParqueaderoCarro(ParqueaderoCarro parqueaderoCarro) {
		return parqueaderoCarroRepository.save(parqueaderoCarro);
	}

}
