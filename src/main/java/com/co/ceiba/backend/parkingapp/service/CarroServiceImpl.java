package com.co.ceiba.backend.parkingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.dto.CarroDTO;
import com.co.ceiba.backend.parkingapp.reposity.CarroRepository;

@Service("carroService")
public class CarroServiceImpl implements VehiculoService<CarroDTO> {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private UtilMapperService utilMapperService;

	@Override
	public CarroDTO guardarVehiculo(CarroDTO carroDTO) {
		Carro carro = utilMapperService.convertirDeClase1aClase2(carroDTO, CarroDTO.class, Carro.class);
		Carro carroGuardado = carroRepository.save(carro);
		return utilMapperService.convertirDeClase1aClase2(carroGuardado, Carro.class, CarroDTO.class);
	}

	@Override
	public CarroDTO buscarVehiculoPorPlaca(String placa) {
		Carro carro = carroRepository.findByPlaca(placa);
		return utilMapperService.convertirDeClase1aClase2(carro, Carro.class, CarroDTO.class);
	}

}
