package com.co.ceiba.backend.parkingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.reposity.CarroRepository;

@Service("carroService")
public class CarroServiceImpl implements CarroService {

	@Autowired
	private CarroRepository carroRepository;

	@Override
	public Carro agregarCarro(Carro carro) {
		return carroRepository.save(carro);
	}

	@Override
	public Carro obtenerCarro(String placa) {
		return carroRepository.findByPlaca(placa);
	}

}
