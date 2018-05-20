package com.co.ceiba.backend.parkingapp.dto;

import java.time.LocalDateTime;

public class ParqueaderoDTO {

	private Long id;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaRetiro;
	private CarroDTO carroDTO;
	private MotoDTO motoDTO;

	protected ParqueaderoDTO() {
	}

	public ParqueaderoDTO(LocalDateTime fechaIngreso) {
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

	public CarroDTO getCarroDTO() {
		return carroDTO;
	}

	public void setCarroDTO(CarroDTO carroDTO) {
		this.carroDTO = carroDTO;
	}

	public MotoDTO getMotoDTO() {
		return motoDTO;
	}

	public void setMotoDTO(MotoDTO motoDTO) {
		this.motoDTO = motoDTO;
	}

}
