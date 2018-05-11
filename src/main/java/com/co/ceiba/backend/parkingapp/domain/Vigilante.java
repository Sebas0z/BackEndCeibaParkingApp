package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

public class Vigilante {

	public ParqueoCarro registrarParqueoCarro(String placa) {
		return new ParqueoCarro(new Carro(placa), LocalDateTime.now());
	}

	public ParqueoMoto registrarParqueoMoto(String placa, int cilindraje) {
		return new ParqueoMoto(new Moto(placa, cilindraje), LocalDateTime.now());
	}

}
