package com.co.ceiba.backend.parkingapp.service;

import com.co.ceiba.backend.parkingapp.domain.Carro;

public interface CarroService {
	
	public Carro guardarCarro(Carro carro);
	
	public Carro obtenerCarro(String placa);

}
