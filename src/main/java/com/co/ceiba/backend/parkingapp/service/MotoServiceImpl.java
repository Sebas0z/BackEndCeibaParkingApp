package com.co.ceiba.backend.parkingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.reposity.MotoRepository;

@Service("motoService")
public class MotoServiceImpl implements VehiculoService<MotoDTO> {

	@Autowired
	private MotoRepository motoRepository;

	@Autowired
	private UtilMapperService utilMapperService;

	@Override
	public MotoDTO guardarVehiculo(MotoDTO motoDTO) {
		Moto moto = utilMapperService.convertirDeClase1aClase2(motoDTO, MotoDTO.class, Moto.class);
		Moto motoGuardado = motoRepository.save(moto);
		return utilMapperService.convertirDeClase1aClase2(motoGuardado, Moto.class, MotoDTO.class);
	}

	@Override
	public MotoDTO buscarVehiculoPorPlaca(String placa) {
		Moto moto = motoRepository.findByPlaca(placa);
		return utilMapperService.convertirDeClase1aClase2(moto, Moto.class, MotoDTO.class);
	}

}
