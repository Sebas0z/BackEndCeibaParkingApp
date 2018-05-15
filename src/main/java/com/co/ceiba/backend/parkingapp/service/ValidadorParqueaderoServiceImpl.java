package com.co.ceiba.backend.parkingapp.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

@Service("validadorParqueaderoService")
public class ValidadorParqueaderoServiceImpl implements ValidadorParqueaderoService {

	@Override
	public boolean validarSiHayEspacioParaCarro(ParqueaderoCarro... parqueaderoCarros) {
		return parqueaderoCarros.length < 20 ? true : false;
	}

	@Override
	public boolean validarSiHayEspacioParaMoto(ParqueaderoMoto... parqueaderoMotos) {
		return parqueaderoMotos.length < 10 ? true : false;
	}

	@Override
	public boolean validarCondicionPlaca(String placa) {
		Pattern pattern = Pattern.compile("^[A]");
		Matcher matcher = pattern.matcher(placa);

		return matcher.find();
	}

	@Override
	public boolean validarCondicionDia(LocalDateTime fecha) {
		return (fecha.getDayOfWeek() == DayOfWeek.MONDAY) || (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) ? true : false;
	}

}
