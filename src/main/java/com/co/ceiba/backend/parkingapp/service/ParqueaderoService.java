package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import com.co.ceiba.backend.parkingapp.dto.ParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.VehiculoDTO;

public interface ParqueaderoService {

	ParqueaderoDTO guardarParqueadero(ParqueaderoDTO parqueaderoDTO);

	List<ParqueaderoDTO> buscarPorFechaRetiroNulo();

	ParqueaderoDTO buscarPorVehiculoFechaRetiroNulo(VehiculoDTO vehiculoDTO);

}
