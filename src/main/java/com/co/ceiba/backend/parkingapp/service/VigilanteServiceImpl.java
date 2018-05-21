package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	private ValidadorService validadorParqueaderoService;

	@Autowired
	private VehiculoService<CarroDTO> carroService;

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@Autowired
	private ParqueaderoCarroService parqueaderoCarroService;

	@Autowired
	private ParqueaderoMotoService parqueaderoMotoService;

	@Autowired
	private CobrarService cobrarService;

	@Autowired
	private CeldaParqueaderoService celdaParqueaderoService;

	@Override
	public String validarYRegistrarIngresoCarro(String placa, LocalDateTime fechaIngreso) {
		Assert.notNull(placa, "La placa no debe ser nulo");
		Assert.notNull(fechaIngreso, "La fecha de ingreso no debe ser nulo");

		ParqueaderoCarro[] arregloParqueaderoCarro = obtenerCarrosParqueados();

		/*
		 * if (validadorParqueaderoService.validarSiHayEspacioParaVehiculo(
		 * arregloParqueaderoCarro)) { if
		 * (!validadorParqueaderoService.validarCondicionPlaca(placa)) { return
		 * guardarParqueaderoCarro(placa, fechaIngreso); } else if
		 * (validadorParqueaderoService.validarCondicionDiaSegunDiasDeLaSemana(
		 * fechaIngreso)) { return guardarParqueaderoCarro(placa, fechaIngreso); } else
		 * { return "El carro con placa " + placa +
		 * " no esta autorizado para ingresar al parqueadero"; } } else { return
		 * "No hay mas espacio para carros en el parqueadero"; }
		 */
		return null;
	}

	private ParqueaderoCarro[] obtenerCarrosParqueados() {
		List<ParqueaderoCarro> listaParqueaderoCarro = parqueaderoCarroService.obtenerCarrosParqueados();
		return listaParqueaderoCarro.toArray(new ParqueaderoCarro[listaParqueaderoCarro.size()]);
	}

	private String guardarParqueaderoCarro(String placa, LocalDateTime fechaIngreso) {
		CarroDTO carro = carroService.buscarVehiculoPorPlaca(placa);

		if (carro == null) {
			carro = carroService.guardarVehiculo(new CarroDTO(placa));
		}

		// parqueaderoCarroService.guardarParqueaderoCarro(new ParqueaderoCarro(carro,
		// fechaIngreso));

		return "Carro registrado en el parqueadero";
	}

	@Override
	public String validarYRegistrarIngresoMoto(String placa, int cilindraje, LocalDateTime fechaIngreso) {
		Assert.notNull(placa, "La placa no debe ser nulo");
		Assert.notNull(cilindraje, "El cilindraje no debe ser nulo");
		Assert.notNull(fechaIngreso, "La fecha de ingreso no debe ser nulo");

		ParqueaderoMoto[] arregloParqueaderoMoto = obtenerMotosParqueadas();

		/*
		 * if (validadorParqueaderoService.validarSiHayEspacioParaMoto(
		 * arregloParqueaderoMoto)) { if
		 * (!validadorParqueaderoService.validarCondicionPlaca(placa)) { return
		 * guardarParqueaderoMoto(placa, cilindraje, fechaIngreso); } else if
		 * (validadorParqueaderoService.validarCondicionDiaSegunDiasDeLaSemana(
		 * fechaIngreso)) { return guardarParqueaderoMoto(placa, cilindraje,
		 * fechaIngreso); } else { return "La moto con placa " + placa +
		 * " no esta autorizado para ingresar al parqueadero"; } } else { return
		 * "No hay mas espacio para motos en el parqueadero"; }
		 */
		return null;
	}

	private ParqueaderoMoto[] obtenerMotosParqueadas() {
		List<ParqueaderoMoto> listaParqueaderoMoto = parqueaderoMotoService.obtenerMotosParqueadas();
		return listaParqueaderoMoto.toArray(new ParqueaderoMoto[listaParqueaderoMoto.size()]);
	}

	private String guardarParqueaderoMoto(String placa, int cilindraje, LocalDateTime fechaIngreso) {
		MotoDTO moto = motoService.buscarVehiculoPorPlaca(placa);

		if (moto == null) {
			moto = motoService.guardarVehiculo(new MotoDTO(placa, cilindraje));
		}

		// parqueaderoMotoService.guardarParqueaderoMoto(new ParqueaderoMoto(moto,
		// fechaIngreso));

		return "Moto registrada en el parqueadero";
	}

	@Override
	public String cobrarRetiroVehiculo(String placa, LocalDateTime fechaRetiro) {

		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = celdaParqueaderoService.buscarVehiculosParqueados();

		CeldaParqueaderoDTO celdaParqueaderoDTO = listCeldaParqueaderoDTO.stream()
				.filter(p -> (p.getCarro().getPlaca() == placa) || (p.getMoto().getPlaca() == placa)).findAny()
				.orElse(null);
		
		if (celdaParqueaderoDTO == null) {
			//throw new Exception("No existe vehiculo con esa placa");
		}
		
		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		//int cobroValorTotal = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, valorHora, valorDia,
			//	minHorasValorHora, maximoCilindraje, valorImpuestoCilindraje);

		ParqueaderoCarro parqueaderoCarro = obtenerCarroParqueado(placa);

		double diferencia = (double) ChronoUnit.HOURS.between(parqueaderoCarro.getFechaIngreso(), fechaRetiro);
		double diferenciaHoras = diferencia < 1 ? 1 : diferencia;
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
		CarroDTO carro = carroService.buscarVehiculoPorPlaca(placa);
		return parqueaderoCarroService.obtenerCarroParqueado(carro);
	}

}
