package com.co.ceiba.backend.parkingapp.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.Parqueadero;

public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {

	List<Parqueadero> findByFechaRetiroIsNull();

	Parqueadero findByCarroAndFechaRetiroIsNull(Carro carro);

	Parqueadero findByMotoAndFechaRetiroIsNull(Moto moto);

}
