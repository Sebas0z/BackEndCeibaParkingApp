package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoCarroRepository;

@Service("parqueaderoCarroService")
public class ParqueaderoCarroServiceImpl implements ParqueaderoCarroService {

	@Autowired
	ParqueaderoCarroRepository parqueaderoCarroRepository;

	@Override
	public ParqueaderoCarro guardarParqueaderoCarro(ParqueaderoCarro parqueaderoCarro) {
		return parqueaderoCarroRepository.save(parqueaderoCarro);
	}

	@Override
	public List<ParqueaderoCarro> obtenerCarrosParqueados() {
		return parqueaderoCarroRepository.findByFechaRetiroIsNull();
	}

	@Override
	public ParqueaderoCarro obtenerCarroParqueado(Carro carro) {
		return parqueaderoCarroRepository.findByCarroAndFechaRetiroIsNull(carro);
	}

}
