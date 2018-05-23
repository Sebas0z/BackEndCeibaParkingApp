package com.co.ceiba.backend.parkingapp.dto;

import java.time.LocalDateTime;

public class CeldaParqueaderoDTO {

	private Long id;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaRetiro;
	private CarroDTO carro;
	private MotoDTO moto;

	protected CeldaParqueaderoDTO() {
	}

	public CeldaParqueaderoDTO(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public CarroDTO getCarro() {
		return carro;
	}

	public void setCarro(CarroDTO carro) {
		this.carro = carro;
	}

	public MotoDTO getMoto() {
		return moto;
	}

	public void setMoto(MotoDTO moto) {
		this.moto = moto;
	}

}
