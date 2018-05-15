package com.co.ceiba.backend.parkingapp.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ParqueaderoCarro extends Parqueadero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Carro carro;

	protected ParqueaderoCarro() {
	}

	public ParqueaderoCarro(Carro carro, LocalDateTime fechaIngreso) {
		super(fechaIngreso);
		this.carro = carro;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro vehiculo) {
		this.carro = vehiculo;
	}

}
