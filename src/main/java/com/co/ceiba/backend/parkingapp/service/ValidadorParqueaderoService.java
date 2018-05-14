package com.co.ceiba.backend.parkingapp.service;

import java.time.LocalDateTime;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public interface ValidadorParqueaderoService {
	
	public boolean validarSiHayEspacioParaCarro(ParqueaderoCarro... parqueaderoCarros);
	
	public boolean validarSiHayEspacioParaMoto(ParqueaderoMoto... parqueaderoMotos);
	
	public boolean validarCondicionPlaca(String placa);
	
	public boolean validarCondicionDia(LocalDateTime dia);

}
