package com.co.ceiba.backend.parkingapp.service;

import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;

public interface CobrarService {

	int calcularValorRetiroVehiculo(CeldaParqueaderoDTO celdaParqueaderoDTO, double valorHora, double valorDia,
			int maxHorasValorHora, int maximoCilindraje, int valorImpuestoCilindraje);

}
