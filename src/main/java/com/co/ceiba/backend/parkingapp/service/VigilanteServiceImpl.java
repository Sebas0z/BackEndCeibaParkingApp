package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
	public String validarYRegistrarIngresoCarro(String placa, LocalDateTime fechaIngreso) {
		Assert.notNull(placa, "La placa no debe ser nulo");
		Assert.notNull(fechaIngreso, "La fecha de ingreso no debe ser nulo");

		ParqueaderoCarro[] arregloParqueaderoCarro = obtenerCarrosParqueados();

		if (validadorParqueaderoService.validarSiHayEspacioParaCarro(arregloParqueaderoCarro)) {
			if (!validadorParqueaderoService.validarCondicionPlaca(placa)) {
				return guardarParqueaderoCarro(placa, fechaIngreso);
			} else if (validadorParqueaderoService.validarCondicionDia(fechaIngreso)) {
				return guardarParqueaderoCarro(placa, fechaIngreso);
			} else {
				return "El carro con placa " + placa + " no esta autorizado para ingresar al parqueadero";
			}
		} else {
			return "No hay mas espacio para carros en el parqueadero";
		}
	}

	private ParqueaderoCarro[] obtenerCarrosParqueados() {
		List<ParqueaderoCarro> listaParqueaderoCarro = parqueaderoCarroService.obtenerCarrosParqueados();
		return listaParqueaderoCarro.toArray(new ParqueaderoCarro[listaParqueaderoCarro.size()]);
	}

	private String guardarParqueaderoCarro(String placa, LocalDateTime fechaIngreso) {
		Carro carro = carroService.obtenerCarro(placa);

		if (carro == null) {
			carro = carroService.guardarCarro(new Carro(placa));
		}

		parqueaderoCarroService.guardarParqueaderoCarro(new ParqueaderoCarro(carro, fechaIngreso));

		return "Carro registrado en el parqueadero";
	}

	@Override
	public String validarYRegistrarIngresoMoto(String placa, int cilindraje, LocalDateTime fechaIngreso) {
		Assert.notNull(placa, "La placa no debe ser nulo");
		Assert.notNull(cilindraje, "El cilindraje no debe ser nulo");
		Assert.notNull(fechaIngreso, "La fecha de ingreso no debe ser nulo");

		ParqueaderoMoto[] arregloParqueaderoMoto = obtenerMotosParqueadas();

		if (validadorParqueaderoService.validarSiHayEspacioParaMoto(arregloParqueaderoMoto)) {
			if (!validadorParqueaderoService.validarCondicionPlaca(placa)) {
				return guardarParqueaderoMoto(placa, cilindraje, fechaIngreso);
			} else if (validadorParqueaderoService.validarCondicionDia(fechaIngreso)) {
				return guardarParqueaderoMoto(placa, cilindraje, fechaIngreso);
			} else {
				return "La moto con placa " + placa + " no esta autorizado para ingresar al parqueadero";
			}
		} else {
			return "No hay mas espacio para motos en el parqueadero";
		}
	}

	private ParqueaderoMoto[] obtenerMotosParqueadas() {
		List<ParqueaderoMoto> listaParqueaderoMoto = parqueaderoMotoService.obtenerMotosParqueadas();
		return listaParqueaderoMoto.toArray(new ParqueaderoMoto[listaParqueaderoMoto.size()]);
	}

	private String guardarParqueaderoMoto(String placa, int cilindraje, LocalDateTime fechaIngreso) {
		Moto moto = motoService.obtenerMoto(placa);

		if (moto == null) {
			moto = motoService.guardarMoto(new Moto(placa, cilindraje));
		}

		parqueaderoMotoService.guardarParqueaderoMoto(new ParqueaderoMoto(moto, fechaIngreso));

		return "Moto registrada en el parqueadero";
	}

	@Override
	public String cobrarRetiroCarro(String placa, LocalDateTime fechaRetiro) {
		Assert.notNull(placa, "La placa no debe ser nulo");
		Assert.notNull(fechaRetiro, "La fecha de retiro no debe ser nulo");

		ParqueaderoCarro parqueaderoCarro = obtenerCarroParqueado(placa);

		double diferenciaHoras = (double) ChronoUnit.HOURS.between(parqueaderoCarro.getFechaIngreso(), fechaRetiro);
		double dias = Math.floor(diferenciaHoras / 24);
		double horas = ((diferenciaHoras / 24) % 1) * 24;

		double valorTotalDias = dias * 8000;
		double valorTotalHoras = horas < 9 ? horas * 1000 : 8000;
		double valorTotal = valorTotalDias + valorTotalHoras;

		parqueaderoCarro.setFechaRetiro(fechaRetiro);

		parqueaderoCarroService.guardarParqueaderoCarro(parqueaderoCarro);

		return Integer.toString((int) valorTotal);
	}

	private ParqueaderoCarro obtenerCarroParqueado(String placa) {
		Carro carro = carroService.obtenerCarro(placa);
		return parqueaderoCarroService.obtenerCarroParqueado(carro);
	}

	@Override
	public String cobrarRetiroMoto(String placa, LocalDateTime fechaRetiro) {
		Assert.notNull(placa, "La placa no debe ser nulo");
		Assert.notNull(fechaRetiro, "La fecha de retiro no debe ser nulo");

		ParqueaderoMoto parqueaderoMoto = obtenerMotoParqueada(placa);

		double diferenciaHoras = (double) ChronoUnit.HOURS.between(parqueaderoMoto.getFechaIngreso(), fechaRetiro);
		double dias = Math.floor(diferenciaHoras / 24);
		double horas = ((diferenciaHoras / 24) % 1) * 24;

		double valorTotalDias = dias * 4000;
		double valorTotalHoras = horas < 9 ? horas * 500 : 4000;
		double valorTotal = valorTotalDias + valorTotalHoras;

		if (parqueaderoMoto.getMoto().getCilindraje() > 500) {
			valorTotal += 2000;
		}

		parqueaderoMoto.setFechaRetiro(fechaRetiro);

		parqueaderoMotoService.guardarParqueaderoMoto(parqueaderoMoto);

		return Integer.toString((int) valorTotal);
	}

	private ParqueaderoMoto obtenerMotoParqueada(String placa) {
		Moto moto = motoService.obtenerMoto(placa);
		return parqueaderoMotoService.obtenerMotoParqueada(moto);
	}

}
