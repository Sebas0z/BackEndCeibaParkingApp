package com.co.ceiba.backend.parkingapp.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.CeldaParqueadero;

public interface CeldaParqueaderoRepository extends JpaRepository<CeldaParqueadero, Long> {

	List<CeldaParqueadero> findByFechaRetiroIsNull();

}
