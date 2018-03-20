package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.FrtFitsFundFlashEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsPageBaseDto;
import com.finruntech.frt.fits.pledge.repository.FrtFitsFundFlashMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* FrtFitsFundFlashServiceImpl
* @author zyinn
* @date 2018/01/26 13:51:49
*/
@Service
public class FrtFitsFundFlashService{
	
	@Autowired
	private FrtFitsFundFlashMapper frtFitsFundFlashDao;

	public PageInfo<?> findFrtFitsFundFlashEntityPageList(FrtFitsFundFlashEntity frtFitsFundFlashEntity, FitsPageBaseDto pageBaseDto){
		StringBuffer orderStr = new StringBuffer(pageBaseDto.getOrderColumn() + " " + pageBaseDto.getOrderBy());
		PageHelper.startPage(pageBaseDto.getPageNum(), pageBaseDto.getPageSize(), orderStr.toString());
		return new PageInfo<>(frtFitsFundFlashDao.findFrtFitsFundFlashEntityPageList(frtFitsFundFlashEntity));
	}

	public int frtFitsFundFlashSave(FrtFitsFundFlashEntity frtFitsFundFlashEntity){
		return frtFitsFundFlashDao.frtFitsFundFlashSave(frtFitsFundFlashEntity);
	}

	public int frtFitsFundFlashModify(FrtFitsFundFlashEntity frtFitsFundFlashEntity){
		return frtFitsFundFlashDao.frtFitsFundFlashModify(frtFitsFundFlashEntity);
	}

	public int frtFitsFundFlashDel(FrtFitsFundFlashEntity frtFitsFundFlashEntity){
		return frtFitsFundFlashDao.frtFitsFundFlashDel(frtFitsFundFlashEntity);
	}

}
