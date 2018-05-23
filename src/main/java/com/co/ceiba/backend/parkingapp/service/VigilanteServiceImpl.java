package com.co.ceiba.backend.parkingapp.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	private ValidadorService validadorService;

	@Autowired
	private VehiculoService<CarroDTO> carroService;

	@Autowired
	private VehiculoService<MotoDTO> motoService;

	@Autowired
	private CobrarService cobrarService;

	@Autowired
	private CeldaParqueaderoService celdaParqueaderoService;

	@Override
	public String registrarIngresoVehiculo(String placa, int cilindraje, LocalDateTime fechaIngreso) {

		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = celdaParqueaderoService.buscarVehiculosParqueados();

		boolean hayEspacio = validarSiHayEspacioParaVehiculo(cilindraje, listCeldaParqueaderoDTO);

		if (!hayEspacio) {
			// throws exception
		}

		List<DayOfWeek> diasSemanaHabiles = new ArrayList<>();
		diasSemanaHabiles.add(DayOfWeek.MONDAY);
		diasSemanaHabiles.add(DayOfWeek.SUNDAY);

		if (validadorService.validarCondicionPlacaSegunPatron(placa, "^[A]")
				&& !validadorService.validarCondicionFechaSegunDiasDeLaSemana(fechaIngreso, diasSemanaHabiles)) {
			// throws exception
		}

		CeldaParqueaderoDTO celdaParqueaderoDTO = prepararCeldaParqueaderoDTO(placa, cilindraje, fechaIngreso);

		celdaParqueaderoService.guardarCeldaParqueadero(celdaParqueaderoDTO);

		return "Vehiculo registrado en el parqueadero";
	}

	private CeldaParqueaderoDTO prepararCeldaParqueaderoDTO(String placa, int cilindraje, LocalDateTime fechaIngreso) {

		CeldaParqueaderoDTO celdaParqueaderoDTO = new CeldaParqueaderoDTO(fechaIngreso);

		if (cilindraje == 0) {
			CarroDTO carro = carroService.guardarVehiculo(new CarroDTO(placa));

			celdaParqueaderoDTO.setCarro(carro);

		} else {
			MotoDTO moto = motoService.guardarVehiculo(new MotoDTO(placa, cilindraje));

			celdaParqueaderoDTO.setMoto(moto);

		}

		return celdaParqueaderoDTO;
	}

	private boolean validarSiHayEspacioParaVehiculo(int cilindraje, List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO) {

		boolean hayEspacio = false;

		if (cilindraje == 0) {
			hayEspacio = validadorService.validarSiHayEspacioParaVehiculo(listCeldaParqueaderoDTO, CarroDTO.class, 20);
		} else {
			hayEspacio = validadorService.validarSiHayEspacioParaVehiculo(listCeldaParqueaderoDTO, MotoDTO.class, 10);
		}

		return hayEspacio;
	}

	@Override
	public String cobrarRetiroVehiculo(String placa, LocalDateTime fechaRetiro) {

		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = celdaParqueaderoService.buscarVehiculosParqueados();

		CeldaParqueaderoDTO celdaParqueaderoDTO = listCeldaParqueaderoDTO.stream()
				.filter(p -> ((p.getCarro() != null) && (placa.equals(p.getCarro().getPlaca())))
						|| ((p.getMoto() != null) && (placa.equals(p.getMoto().getPlaca()))))
				.findAny().orElse(null);

		if (celdaParqueaderoDTO == null) {
			// throws exception
		}

		celdaParqueaderoDTO.setFechaRetiro(fechaRetiro);

		int cobroValorTotal = 0;

		if (celdaParqueaderoDTO.getCarro() != null) {
			cobroValorTotal = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, 1000, 8000, 9, 0, 0);

		} else {
			cobroValorTotal = cobrarService.calcularValorRetiroVehiculo(celdaParqueaderoDTO, 500, 4000, 9, 500, 2000);
		}

		celdaParqueaderoService.guardarCeldaParqueadero(celdaParqueaderoDTO);

		return Integer.toString(cobroValorTotal);
	}

}
