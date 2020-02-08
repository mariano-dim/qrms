package com.ffx11.qrms.service;

import com.ffx11.qrms.entity.CodigoQR;

import java.util.List;

public interface ICodigoQRService {
	
	public List<CodigoQR> findAll();

	public void delete(long id);

	public CodigoQR findById(Long id);

	public CodigoQR save(CodigoQR codigoQR);


}
