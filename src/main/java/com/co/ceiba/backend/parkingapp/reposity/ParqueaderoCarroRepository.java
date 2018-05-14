package com.co.ceiba.backend.parkingapp.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.Carro;
import com.co.ceiba.backend.parkingapp.domain.ParqueaderoCarro;

public interface ParqueaderoCarroRepository extends JpaRepository<ParqueaderoCarro, Long> {

	List<ParqueaderoCarro> findByFechaRetiroIsNull();

	ParqueaderoCarro findByCarroAndFechaRetiroIsNull(Carro carro);

}
