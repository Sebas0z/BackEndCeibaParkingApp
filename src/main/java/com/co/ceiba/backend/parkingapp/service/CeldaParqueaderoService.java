package com.co.ceiba.backend.parkingapp.service;

import java.util.List;

import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;

public interface CeldaParqueaderoService {

	CeldaParqueaderoDTO guardarCeldaParqueadero(CeldaParqueaderoDTO celdaParqueaderoDTO);

	List<CeldaParqueaderoDTO> buscarVehiculosParqueados();
}
