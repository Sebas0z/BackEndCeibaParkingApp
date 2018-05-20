package com.co.ceiba.backend.parkingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.Parqueadero;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.dto.MotoDTO;
import com.co.ceiba.backend.parkingapp.dto.ParqueaderoDTO;
import com.co.ceiba.backend.parkingapp.dto.VehiculoDTO;
import com.co.ceiba.backend.parkingapp.reposity.ParqueaderoRepository;

public class ParqueaderoServiceImpl implements ParqueaderoService {

	@Autowired
	private ParqueaderoRepository parqueaderoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public ParqueaderoDTO guardarParqueadero(ParqueaderoDTO parqueaderoDTO) {
		Parqueadero parqueadero = parqueaderoRepository.save(modelMapper.map(parqueaderoDTO, Parqueadero.class));
		return modelMapper.map(parqueadero, ParqueaderoDTO.class);
	}

	@Override
	public List<ParqueaderoDTO> buscarPorFechaRetiroNulo() {
		List<ParqueaderoDTO> listParqueaderoDTO = new ArrayList<>();

		for (Parqueadero parqueadero : parqueaderoRepository.findAll()) {
			listParqueaderoDTO.add(modelMapper.map(parqueadero, ParqueaderoDTO.class));
		}

		return listParqueaderoDTO;
	}

	@Override
	public ParqueaderoDTO buscarPorVehiculoFechaRetiroNulo(VehiculoDTO vehiculoDTO) {
		if (vehiculoDTO instanceof CarroDTO) {
			
			CarroDTO carroDTO = (CarroDTO) vehiculoDTO;
			Carro carro = modelMapper.map(carroDTO, Carro.class);
			Parqueadero parqueadero = parqueaderoRepository.findByCarroAndFechaRetiroIsNull(carro);
			
			return modelMapper.map(parqueadero, ParqueaderoDTO.class);
			
		} else if(vehiculoDTO instanceof MotoDTO) {
			
			MotoDTO motoDTO = (MotoDTO) vehiculoDTO;
			Moto moto = modelMapper.map(motoDTO, Moto.class);
			Parqueadero parqueadero = parqueaderoRepository.findByMotoAndFechaRetiroIsNull(moto);
			
			return modelMapper.map(parqueadero, ParqueaderoDTO.class);
			
		} else {
		}

		return null;
	}

}
