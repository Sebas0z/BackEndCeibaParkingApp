package com.co.ceiba.backend.parkingapp.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoMoto;

public interface ParqueaderoMotoRepository extends JpaRepository<ParqueaderoMoto, Long> {

}
