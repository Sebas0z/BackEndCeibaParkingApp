package com.co.ceiba.backend.parkingapp.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.Moto;

public interface MotoRepository extends JpaRepository<Moto, Long> {

	Moto findByPlaca(String placa);

}
