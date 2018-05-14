package com.co.ceiba.backend.parkingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.reposity.MotoRepository;

@Service("motoService")
public class MotoServiceImpl implements MotoService {

	@Autowired
	private MotoRepository motoRepository;

	@Override
	public Moto agregarMoto(Moto moto) {
		return motoRepository.save(moto);
	}

	@Override
	public Moto obtenerMoto(String placa) {
		return motoRepository.findByPlaca(placa);
	}

}
