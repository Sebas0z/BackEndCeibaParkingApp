package com.co.ceiba.backend.parkingapp.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;

public interface ParqueaderoCarroRepository extends JpaRepository<ParqueaderoCarro, Long> {

}
