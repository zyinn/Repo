package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgBondMgtEntityDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* FrtFitsBondmgtFlashMapper
* @author zyinn
* @date 2018/01/26 13:42:28
*/
@Repository("frtFitsBondmgtFlashMapper")
public interface FrtFitsBondmgtFlashMapper<T> {

	FitsRepoPldgBondMgtEntityDto checkRepoPldgBondMgt(T t);

	int updateRepoBondAvailableGiveBack(List list);

	int updateRepoBondAvailable(List<T> list);//FitsRepoPldgBondMgtEntityDto

	List<FitsRepoPldgBondMgtEntityDto> findRepoPldgAllBondMgt(T t);

	List<FitsRepoPldgBondMgtEntityDto> findRepoPldgBondMgt(T t);

	List<FitsRepoPldgBondMgtEntityDto> queryFitsRepoPldgBondMgt(T t);
}
