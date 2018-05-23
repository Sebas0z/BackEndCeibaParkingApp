package com.co.ceiba.backend.parkingapp.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.backend.parkingapp.service.VigilanteService;

@RestController
@RequestMapping("vigilante")
public class VigilanteRestController {

	@Autowired
	private VigilanteService vigilanteService;

	@GetMapping("registrarIngresoVehiculo")
	public String ingresarMoto(@RequestParam String placa, @RequestParam(defaultValue = "0") int cilindraje) {
		return vigilanteService.registrarIngresoVehiculo(placa, cilindraje, LocalDateTime.now());
	}

	@GetMapping("cobrarRetiroVehiculo")
	public String cobrarRetiroCarro(@RequestParam String placa) {
		return vigilanteService.cobrarRetiroVehiculo(placa, LocalDateTime.now());
	}

}
