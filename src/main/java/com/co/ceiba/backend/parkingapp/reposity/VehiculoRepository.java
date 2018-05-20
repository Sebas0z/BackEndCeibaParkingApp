package com.co.ceiba.backend.parkingapp.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.co.ceiba.backend.parkingapp.domain.Vehiculo;

@NoRepositoryBean
public interface VehiculoRepository<T extends Vehiculo> extends JpaRepository<T, Long> {

	T findByPlaca(String placa);
}
