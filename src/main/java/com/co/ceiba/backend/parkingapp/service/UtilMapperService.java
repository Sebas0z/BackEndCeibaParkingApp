package com.co.ceiba.backend.parkingapp.service;

public interface UtilMapperService {

	<T, Y> Y convertirDeClase1aClase2(Object objeto, Class<T> deClase1, Class<Y> aClase2);

}
