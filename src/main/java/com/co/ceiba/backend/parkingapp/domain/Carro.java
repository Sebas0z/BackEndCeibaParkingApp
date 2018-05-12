package com.co.ceiba.backend.parkingapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carro extends Vehiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	public Carro(String placa) {
		super(placa);
	}
	
}
