package com.ffx11.qrms.service;

import com.ffx11.qrms.dao.ICodigoQRDao;
import com.ffx11.qrms.entity.CodigoQR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CodigoQRServiceImpl implements ICodigoQRService {

	@Autowired
	private ICodigoQRDao iCodigoQRDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CodigoQR> findAll() {
		return (List<CodigoQR>) iCodigoQRDao.findAll();
	}

	@Override
	@Transactional()
	public void delete(long id) {
		iCodigoQRDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public CodigoQR findById(Long id) {
		return iCodigoQRDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public CodigoQR save(CodigoQR cliente) {
		return iCodigoQRDao.save(cliente);
	}
}
