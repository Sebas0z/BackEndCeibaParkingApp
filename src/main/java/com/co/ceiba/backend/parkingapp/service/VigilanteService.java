package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;

public interface VigilanteService {

	public String validarYRegistrarIngresoCarro(String placa, LocalDateTime dia);

	public String registrarIngresoCarro(String placa, LocalDateTime dia);

	public String validarYRegistrarIngresoMoto(String placa, int cilindraje, LocalDateTime dia);
	
	public String registrarIngresoMoto(String placa, int cilindraje, LocalDateTime dia);
	
	public String cobrarRetiroCarro(String placa, LocalDateTime dia);
	
	public String cobrarRetiroMoto(String placa, LocalDateTime dia);

}
