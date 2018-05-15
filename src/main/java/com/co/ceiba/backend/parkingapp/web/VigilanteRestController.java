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

	@GetMapping("ingresarCarro")
	public String ingresarCarro(@RequestParam String placa) {
		return vigilanteService.validarYRegistrarIngresoCarro(placa, LocalDateTime.now());
	}

	@GetMapping("ingresarMoto")
	public String ingresarMoto(@RequestParam String placa, @RequestParam int cilindraje) {
		return vigilanteService.validarYRegistrarIngresoMoto(placa, cilindraje, LocalDateTime.now());
	}

	@GetMapping("cobrarRetiroCarro")
	public String cobrarRetiroCarro(@RequestParam String placa) {
		return vigilanteService.cobrarRetiroCarro(placa, LocalDateTime.now());
	}

	@GetMapping("cobrarRetiroMoto")
	public String cobrarRetiroMoto(@RequestParam String placa) {
		return vigilanteService.cobrarRetiroMoto(placa, LocalDateTime.now());
	}

}
