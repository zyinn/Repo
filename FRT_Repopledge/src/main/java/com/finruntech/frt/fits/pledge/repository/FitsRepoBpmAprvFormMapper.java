package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.FitsBpmAprvFormEntity;
import org.springframework.stereotype.Repository;

/**
 * 审批单BPMMapper
 * Created by Zhangws on 2018/1/3.
 */
@Repository("fitsRepoBpmAprvFormMapper")
public interface FitsRepoBpmAprvFormMapper {
        /**
         * 新增审批单
         * @param e FitsBpmAprvFormEntity
         * @return
         */
        int saveFitsBpmAprvFormEntity(FitsBpmAprvFormEntity e);

        /**
         * 修改审批单
         * @param e FitsBpmAprvFormEntity
         * @return
         */
        int updateFitsBpmAprvFormEntity(FitsBpmAprvFormEntity e);

        /**
         * 删除审批单
         * @param e FitsBpmAprvFormEntity
         * @return
         */
        int delFitsBpmAprvFormEntity(FitsBpmAprvFormEntity e);

        /**
         * 根据审批单号查询审批信息
         * @param aprvFormNum 审批号
         * @return FitsBpmAprvFormEntity
         */
        FitsBpmAprvFormEntity findFitsBpmApryFromEntity(String aprvFormNum);
}
