package com.co.ceiba.backend.parkingapp.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.ceiba.backend.parkingapp.domain.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

	Carro findByPlaca(String placa);

}
