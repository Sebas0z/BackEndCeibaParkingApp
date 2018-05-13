package com.co.ceiba.backend.parkingapp.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	private CarroService carroService;

	@Autowired
	private ParqueaderoCarroService parqueaderoCarroService;

	@Override
	public String validarYRegistrarIngresoCarro(String placa, LocalDateTime dia) {
		List<ParqueaderoCarro> listaParqueaderoCarro = parqueaderoCarroService.obtenerTodos();
		ParqueaderoCarro[] arregloParqueaderoCarro = listaParqueaderoCarro
				.toArray(new ParqueaderoCarro[listaParqueaderoCarro.size()]);

		if (validarSiHayEspacioParaCarro(arregloParqueaderoCarro)) {
			if (!validarCondicionPlaca(placa)) {
				return registrarIngresoCarro(placa, dia);
			} else if (validarCondicionDia(dia)) {
				return registrarIngresoCarro(placa, dia);
			} else {
				return "El carro con placa " + placa + " no esta autorizado para ingresar al parqueadero";
			}
		} else {
			return "No hay mas espacio para carros en el parqueadero";
		}
	}

	@Override
	public String registrarIngresoCarro(String placa, LocalDateTime dia) {
		parqueaderoCarroService
				.agregarParqueaderoCarro(new ParqueaderoCarro(carroService.agregarCarro(new Carro(placa)), dia));

		return "Carro registrado en el parqueadero";
	}

	@Override
	public void registrarIngresoMoto(String placa, int cilindraje, LocalDateTime dia) {
		// TODO Auto-generated method stub

	}

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
	public boolean validarCondicionDia(LocalDateTime dia) {
		return (dia.getDayOfWeek() == DayOfWeek.MONDAY) || (dia.getDayOfWeek() == DayOfWeek.SUNDAY) ? true : false;
	}

}
