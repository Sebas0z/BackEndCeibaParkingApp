package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	private ValidadorParqueaderoService validadorParqueaderoService;

	@Autowired
	private CarroService carroService;

	@Autowired
	private MotoService motoService;

	@Autowired
	private ParqueaderoCarroService parqueaderoCarroService;

	@Autowired
	private ParqueaderoMotoService parqueaderoMotoService;

	@Override
	public String validarYRegistrarIngresoCarro(String placa, LocalDateTime dia) {
		List<ParqueaderoCarro> listaParqueaderoCarro = parqueaderoCarroService.obtenerTodos();
		ParqueaderoCarro[] arregloParqueaderoCarro = listaParqueaderoCarro
				.toArray(new ParqueaderoCarro[listaParqueaderoCarro.size()]);

		if (validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarro)) {
			if (!validadorParqueaderoService.validarCondicionPlaca(placa)) {
				return registrarIngresoCarro(placa, dia);
			} else if (validadorParqueaderoService.validarCondicionDia(dia)) {
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
	public String validarYRegistrarIngresoMoto(String placa, int cilindraje, LocalDateTime dia) {
		List<ParqueaderoMoto> listaParqueaderoMoto = parqueaderoMotoService.obtenerTodos();
		ParqueaderoMoto[] arregloParqueaderoMoto = listaParqueaderoMoto
				.toArray(new ParqueaderoMoto[listaParqueaderoMoto.size()]);

		if (validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMoto)) {
			if (!validadorParqueaderoService.validarCondicionPlaca(placa)) {
				return registrarIngresoMoto(placa, cilindraje, dia);
			} else if (validadorParqueaderoService.validarCondicionDia(dia)) {
				return registrarIngresoMoto(placa, cilindraje, dia);
			} else {
				return "La moto con placa " + placa + " no esta autorizado para ingresar al parqueadero";
			}
		} else {
			return "No hay mas espacio para motos en el parqueadero";
		}
	}

	@Override
	public String registrarIngresoMoto(String placa, int cilindraje, LocalDateTime dia) {
		parqueaderoMotoService
				.agregarParqueaderoMoto(new ParqueaderoMoto(motoService.agregarMoto(new Moto(placa, cilindraje)), dia));

		return "Moto registrado en el parqueadero";
	}

}
