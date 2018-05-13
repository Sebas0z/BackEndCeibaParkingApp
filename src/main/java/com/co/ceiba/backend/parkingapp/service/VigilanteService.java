package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDate;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public interface VigilanteService {
	
	public void registrarIngresoCarro(String placa);
	
	public void registrarIngresoMoto(String placa, int cilindraje);
	
	public boolean validarSiHayMenosDeVeinteCarros(ParqueaderoCarro... parqueaderoCarros);
	
	public boolean validarSiHayMenosDeDiesMotos(ParqueaderoMoto... parqueaderoMotos);
	
	public boolean validarPlacaConInicialA(String placa);
	
	public boolean validarSiDiaEsLunesODomingo(LocalDate dia);

}
