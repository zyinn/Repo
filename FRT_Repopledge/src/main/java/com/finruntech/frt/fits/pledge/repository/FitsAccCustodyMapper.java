package com.finruntech.frt.fits.pledge.repository;

/**
 * Created by weihubin on 2018/1/5.
 */

import com.finruntech.frt.fits.pledge.model.FitsAccCustodyCashEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsAccCustodySecuDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fitsAccCustodyMapper")
public interface FitsAccCustodyMapper {

    List<FitsAccCustodyCashEntity> findAccCusCashByPortCash(String fPortfacctCash);

    List<FitsAccCustodySecuDto> findAccCusSecByPortCash(String fPortfacctCash);

}
