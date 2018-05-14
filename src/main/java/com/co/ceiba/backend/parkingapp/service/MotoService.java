package com.co.ceiba.backend.parkingapp.service;

import com.co.ceiba.backend.parkingapp.domain.Moto;

public interface MotoService {
	
	public Moto guardarMoto(Moto moto);
	
	public Moto obtenerMoto(String placa);

}
