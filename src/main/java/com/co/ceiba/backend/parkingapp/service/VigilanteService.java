package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;

public interface VigilanteService {

	public String validarYRegistrarIngresoCarro(String placa, LocalDateTime fechaIngreso);

	public String validarYRegistrarIngresoMoto(String placa, int cilindraje, LocalDateTime fechaIngreso);

	public String cobrarRetiroVehiculo(String placa, LocalDateTime fechaRetiro);

}
