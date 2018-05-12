package com.co.ceiba.backend.parkingapp.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vigilante {

	/*
	 * public ParqueoCarro registrarParqueoCarro(String placa) { return new
	 * ParqueoCarro(new Carro(placa), LocalDateTime.now()); }
	 * 
	 * public ParqueoMoto registrarParqueoMoto(String placa, int cilindraje) {
	 * return new ParqueoMoto(new Moto(placa, cilindraje), LocalDateTime.now()); }
	 */

	public boolean validarSiHayMenosDeVeinteCarros(List<ParqueoCarro> parqueoCarros) {
		return parqueoCarros.size() < 20 ? true : false;
	}

	public boolean validarSiHayMenosDeDiesMotos(List<ParqueoMoto> parqueoMotos) {
		return parqueoMotos.size() < 10 ? true : false;
	}

	public boolean validarPlacaConInicialA(String placa) {
		Pattern pattern = Pattern.compile("^[A]");
		Matcher matcher = pattern.matcher(placa);

		return matcher.find();
	}

	public boolean validarSiDiaEsLunesODomingo(LocalDate dia) {
		return (dia.getDayOfWeek() == DayOfWeek.MONDAY) || (dia.getDayOfWeek() == DayOfWeek.SUNDAY) ? true : false;
	}

}
