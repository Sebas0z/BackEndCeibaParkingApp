package com.co.ceiba.backend.parkingapp.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
		List<ParqueaderoCarro> listaParqueaderoCarro = parqueaderoCarroService.obtenerCarrosParqueados();
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
		List<ParqueaderoMoto> listaParqueaderoMoto = parqueaderoMotoService.obtenerMotosParqueadas();
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

	@Override
	public String cobrarRetiroCarro(String placa, LocalDateTime dia) {
		Carro carro = carroService.obtenerCarro(placa);
		ParqueaderoCarro parqueaderoCarro = parqueaderoCarroService.obtenerCarroParqueado(carro);

		long hours = ChronoUnit.HOURS.between(parqueaderoCarro.getFechaIngreso(), dia);
		
		// horas / 24
		// trunc resultado para dias
		// los decimales se multiplican por 24 para obtener las horas y listo
		
		return "1000";
	}

	@Override
	public String cobrarRetiroMoto(String placa, LocalDateTime dia) {
		// TODO Auto-generated method stub
		return null;
	}

}
