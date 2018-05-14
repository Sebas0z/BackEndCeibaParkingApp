package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;

public interface ParqueaderoCarroService {
	
	public ParqueaderoCarro agregarParqueaderoCarro(ParqueaderoCarro parqueaderoCarro);
	
	public List<ParqueaderoCarro> obtenerCarrosParqueados();
	
	public ParqueaderoCarro obtenerCarroParqueado(Carro carro);

}
