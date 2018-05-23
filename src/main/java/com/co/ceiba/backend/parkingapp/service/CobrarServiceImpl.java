package com.co.ceiba.backend.parkingapp.service;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;

@Service("cobrarService")
public class CobrarServiceImpl implements CobrarService {

	@Override
	public int calcularValorRetiroVehiculo(CeldaParqueaderoDTO celdaParqueaderoDTO, double valorHora, double valorDia,
			int maxHorasValorHora, int maximoCilindraje, int valorImpuestoCilindraje) {

		double diferencia = (double) ChronoUnit.HOURS.between(celdaParqueaderoDTO.getFechaIngreso(),
				celdaParqueaderoDTO.getFechaRetiro());
		double diferenciaHoras = diferencia < 1 ? 1 : diferencia;
		double dias = Math.floor(diferenciaHoras / 24);
		double horas = ((diferenciaHoras / 24) % 1) * 24;

		double valorTotalDias = dias * valorDia;
		double valorTotalHoras = horas < maxHorasValorHora ? horas * valorHora : valorDia;
		double valorTotal = valorTotalDias + valorTotalHoras;

		if ((celdaParqueaderoDTO.getMoto() != null) && (celdaParqueaderoDTO.getMoto().getCilindraje() != 0)
				&& (maximoCilindraje != 0) && (valorImpuestoCilindraje != 0)
				&& (celdaParqueaderoDTO.getMoto().getCilindraje() > maximoCilindraje)) {
			valorTotal += valorImpuestoCilindraje;
		}

		return (int) valorTotal;
	}

}
