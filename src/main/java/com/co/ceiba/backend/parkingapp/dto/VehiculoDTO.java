package com.co.ceiba.backend.parkingapp.dto;

public abstract class VehiculoDTO {

	private Long id;
	private String placa;
	
	protected VehiculoDTO() {
	}

	public VehiculoDTO(String placa) {
		this.placa = placa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
