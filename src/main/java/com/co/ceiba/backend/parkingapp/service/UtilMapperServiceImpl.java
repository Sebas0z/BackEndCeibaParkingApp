package com.co.ceiba.backend.parkingapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("utilMapperService")
public class UtilMapperServiceImpl implements UtilMapperService {

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public <T, Y> Y convertirDeClase1aClase2(Object objeto, Class<T> deClase1, Class<Y> aClase2) {
		T objetoClase1 = deClase1.cast(objeto);
		return modelMapper.map(objetoClase1, aClase2);
	}

}
