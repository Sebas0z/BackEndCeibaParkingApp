package com.co.ceiba.backend.parkingapp.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	private CarroService carroService;

	@Override
	public void registrarIngresoCarro(String placa) {
		//if (validarPlacaConInicialA(placa) && validarSiDiaEsLunesODomingo(LocalDate.now())) {
			//carroService.agregarCarro(placa);
		//}
	}

	@Override
	public void registrarIngresoMoto(String placa, int cilindraje) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean validarSiHayMenosDeVeinteCarros(ParqueaderoCarro... parqueaderoCarros) {
		return parqueaderoCarros.length < 20 ? true : false;
	}
	
	@Override
	public boolean validarSiHayMenosDeDiesMotos(ParqueaderoMoto... parqueaderoMotos) {
		return parqueaderoMotos.length < 10 ? true : false;
	}
	
	@Override
	public boolean validarPlacaConInicialA(String placa) {
		Pattern pattern = Pattern.compile("^[A]");
		Matcher matcher = pattern.matcher(placa);

		return matcher.find();
	}
	
	@Override
	public boolean validarSiDiaEsLunesODomingo(LocalDate dia) {
		return (dia.getDayOfWeek() == DayOfWeek.MONDAY) || (dia.getDayOfWeek() == DayOfWeek.SUNDAY) ? true : false;
	}

}
