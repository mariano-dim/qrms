package com.ffx11.qrms.dao;

import com.ffx11.qrms.dao.ICodigoQRDao;
import com.ffx11.qrms.entity.CodigoQR;
import org.springframework.data.repository.CrudRepository;

public interface ICodigoQRDao extends CrudRepository<CodigoQR, Long> {

}
