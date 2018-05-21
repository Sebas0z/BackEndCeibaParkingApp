package com.co.ceiba.backend.parkingapp.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.dto.VehiculoDTO;

@Service("validadorParqueaderoService")
public class ValidadorServiceImpl implements ValidadorService {

	@Override
	public <T extends VehiculoDTO> boolean validarSiHayEspacioParaVehiculo(
			List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO, Class<T> vehiculoDTO, int limite) {

		List<CeldaParqueaderoDTO> resultado = new ArrayList<>();

		if (vehiculoDTO == CarroDTO.class) {
			resultado = listCeldaParqueaderoDTO.stream().filter(x -> x.getCarro() != null).collect(Collectors.toList());
		} else if (vehiculoDTO == MotoDTO.class) {
			resultado = listCeldaParqueaderoDTO.stream().filter(x -> x.getMoto() != null).collect(Collectors.toList());
		}

		return resultado.size() < limite;
	}

	@Override
	public boolean validarCondicionPlacaSegunPatron(String placa, String patron) {
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(placa);

		return matcher.find();
	}

	@Override
	public boolean validarCondicionFechaSegunDiasDeLaSemana(LocalDateTime fecha, List<DayOfWeek> diasSemanas) {
		return diasSemanas.stream().anyMatch(x -> x == fecha.getDayOfWeek());
	}

}
