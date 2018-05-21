package com.co.ceiba.backend.parkingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.CeldaParqueadero;
import com.co.ceiba.backend.parkingapp.dto.CeldaParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.reposity.CeldaParqueaderoRepository;

@Service("celdaParqueaderoService")
public class CeldaParqueaderoServiceImpl implements CeldaParqueaderoService {

	@Autowired
	private CeldaParqueaderoRepository parqueaderoRepository;

	@Autowired
	private UtilMapperService utilMapperService;

	@Override
	public CeldaParqueaderoDTO guardarCeldaParqueadero(CeldaParqueaderoDTO parqueaderoDTO) {
		CeldaParqueadero celdaParqueadero = utilMapperService.convertirDeClase1aClase2(parqueaderoDTO,
				CeldaParqueaderoDTO.class, CeldaParqueadero.class);
		CeldaParqueadero celdaParqueaderoGuardado = parqueaderoRepository.save(celdaParqueadero);
		return utilMapperService.convertirDeClase1aClase2(celdaParqueaderoGuardado, CeldaParqueadero.class,
				CeldaParqueaderoDTO.class);
	}

	@Override
	public List<CeldaParqueaderoDTO> buscarVehiculosParqueados() {
		List<CeldaParqueaderoDTO> listCeldaParqueaderoDTO = new ArrayList<>();

		for (CeldaParqueadero celdaParqueadero : parqueaderoRepository.findByFechaRetiroIsNull()) {
			CeldaParqueaderoDTO celdaParqueaderoDTO = utilMapperService.convertirDeClase1aClase2(celdaParqueadero,
					CeldaParqueadero.class, CeldaParqueaderoDTO.class);
			listCeldaParqueaderoDTO.add(celdaParqueaderoDTO);
		}

		return listCeldaParqueaderoDTO;
	}

}
