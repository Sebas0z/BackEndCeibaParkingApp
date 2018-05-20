package com.co.ceiba.backend.parkingapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.reposity.CarroRepository;

@Service("carroService")
public class CarroServiceImpl implements VehiculoService<CarroDTO> {

	@Autowired
	private CarroRepository carroRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public CarroDTO guardarVehiculo(CarroDTO carroDTO) {
		Carro carro = carroRepository.save(modelMapper.map(carroDTO, Carro.class));
		return modelMapper.map(carro, CarroDTO.class);
	}

	@Override
	public CarroDTO buscarVehiculoPorPlaca(String placa) {
		Carro carro = carroRepository.findByPlaca(placa);
		return modelMapper.map(carro, CarroDTO.class);
	}

}
