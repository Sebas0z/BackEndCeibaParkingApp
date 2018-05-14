package com.co.ceiba.backend.parkingapp.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.Moto;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public interface ParqueaderoMotoRepository extends JpaRepository<ParqueaderoMoto, Long> {

	List<ParqueaderoMoto> findByFechaRetiroIsNull();

	ParqueaderoMoto findByMotoAndFechaRetiroIsNull(Moto moto);

}
