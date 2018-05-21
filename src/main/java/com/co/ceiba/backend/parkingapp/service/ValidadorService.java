package com.co.ceiba.backend.parkingapp.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.VehiculoDTO;

public interface ValidadorService {

	<T extends VehiculoDTO> boolean validarSiHayEspacioParaVehiculo(List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO,
			Class<T> vehiculoDTO, int limite);

	boolean validarCondicionPlacaSegunPatron(String placa, String patron);

	boolean validarCondicionFechaSegunDiasDeLaSemana(LocalDateTime fecha, List<DayOfWeek> diasSemanas);

}
