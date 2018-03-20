package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.FrtFitsFundFlashEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* FrtFitsFundFlashMapper
* @author zyinn
* @date 2018/01/26 13:51:49
*/
@Repository("FrtFitsFundFlashMapper")
public interface FrtFitsFundFlashMapper {
    //分页查询
	List<FrtFitsFundFlashEntity> findFrtFitsFundFlashEntityPageList(FrtFitsFundFlashEntity frtFitsFundFlashEntity);

	//新增
	int frtFitsFundFlashSave(FrtFitsFundFlashEntity frtFitsFundFlashEntity);
	
	//修改
	int frtFitsFundFlashModify(FrtFitsFundFlashEntity frtFitsFundFlashEntity);
	
	//删除
	int frtFitsFundFlashDel(FrtFitsFundFlashEntity frtFitsFundFlashEntity);

}
