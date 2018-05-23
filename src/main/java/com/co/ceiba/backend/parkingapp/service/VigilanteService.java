package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;

public interface VigilanteService {

	String registrarIngresoVehiculo(String placa, int cilindraje, LocalDateTime fechaIngreso);

	String cobrarRetiroVehiculo(String placa, LocalDateTime fechaRetiro);

}
