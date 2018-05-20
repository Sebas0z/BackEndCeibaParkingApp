package com.co.ceiba.backend.parkingapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.reposity.MotoRepository;

@Service("motoService")
public class MotoServiceImpl implements VehiculoService<MotoDTO> {

	@Autowired
	private MotoRepository motoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public MotoDTO guardarVehiculo(MotoDTO motoDTO) {
		Moto moto = motoRepository.save(modelMapper.map(motoDTO, Moto.class));
		return modelMapper.map(moto, MotoDTO.class);
	}

	@Override
	public MotoDTO buscarVehiculoPorPlaca(String placa) {
		Moto moto = motoRepository.findByPlaca(placa);
		return modelMapper.map(moto, MotoDTO.class);
	}

}
