package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParqueaderoMoto extends Parqueadero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	private Moto moto;

	public ParqueaderoMoto(Moto moto, LocalDateTime fechaIngreso) {
		super(fechaIngreso);
		this.moto = moto;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

}
