package com.co.ceiba.backend.parkingapp.service;

import com.co.ceiba.backend.parkingapp.dto.VehiculoDTO;

public interface VehiculoService<T extends VehiculoDTO> {

	T guardarVehiculo(T vehiculoDTO);

	T buscarVehiculoPorPlaca(String placa);

}
