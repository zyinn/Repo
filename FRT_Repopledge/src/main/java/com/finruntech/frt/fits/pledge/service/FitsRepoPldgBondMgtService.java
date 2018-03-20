package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgBondMgtDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgBondMgtEntityDto;
import com.finruntech.frt.fits.pledge.repository.FrtFitsBondmgtFlashMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 质押券
 * Created by yinan.zhang on 2017/12/28.
 */
@Service
public class FitsRepoPldgBondMgtService<T> {
    @Autowired
    private FrtFitsBondmgtFlashMapper fitsRepoPldgBondMgtService;

    /**
     * 根据投资组合和资金账户查询质押券
     * @param entity FitsRepoPldgBondMgtDto
     * @return list
     */
    public PageInfo<?> queryFitsRepoPldgBondMgt(FitsRepoPldgBondMgtDto entity) {
        Map<String, String> map = getBondMgtMap(entity);
        return new PageInfo<>(fitsRepoPldgBondMgtService.queryFitsRepoPldgBondMgt(map));
    }

    /**
     * 根据FitsRepoPldgBondMgtDto查询质押券
     * @param entity FitsRepoPldgBondMgtDto
     * @return list
     */
    public PageInfo<?> findRepoPldgBondMgt(FitsRepoPldgBondMgtDto entity) {
        Map<String, String> map = getBondMgtMap(entity);
        map.put("currentDate",entity.getCurrentDate());
        return new PageInfo<>(fitsRepoPldgBondMgtService.findRepoPldgBondMgt(map));
    }

    /**
     * 根据FitsRepoPldgBondMgtDto查询所有质押券
     * @param entity FitsRepoPldgBondMgtDto
     * @return list
     */
    public PageInfo<?> findRepoPldgAllBondMgt(FitsRepoPldgBondMgtDto entity) {
        Map<String, String> map = getBondMgtMap(entity);
        map.put("currentDate",entity.getCurrentDate());
        return new PageInfo<>(fitsRepoPldgBondMgtService.findRepoPldgAllBondMgt(map));
    }

    /**
     *根据Map查询质押券
     * @param entity FitsRepoPldgBondMgtDto
     * @return 查询 质押券
     */
    private Map<String, String> getBondMgtMap(FitsRepoPldgBondMgtDto entity) {
        Map<String, String> map = new HashMap<>();
        map.put("bmPortfolioSecu", entity.getBmPortfolioSecu());
        map.put("bmPortfolioCash", entity.getBmPortfolioCash());
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getOrderColumn() + " " + entity.getOrderBy());
        PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), sb.toString());
        return map;
    }

    /**
     * 质押券质押时修改可用
     * @param list FitsRepoPldgBondMgtEntityDto
     * @return 影响条数
     */
    @Transactional
    public int updateRepoBondAvailable(List<FitsRepoPldgBondMgtEntityDto> list) {
        return  fitsRepoPldgBondMgtService.updateRepoBondAvailable(list);
    }

    /**
     *归还质押券
     * @param list FitsRepoPldgBondMgtEntityDto
     * @return 影响条数
     */
    @Transactional
    public int updateRepoBondAvailableGiveBack(List<FitsRepoPldgBondMgtEntityDto> list) {
        return  fitsRepoPldgBondMgtService.updateRepoBondAvailableGiveBack(list);
    }

    /**
     * 验证可用券
     * @param dto FitsRepoPldgBondMgtEntityDto
     * @return FitsRepoPldgBondMgtEntityDto
     */
    public FitsRepoPldgBondMgtEntityDto checkRepoPldgBondMgt(FitsRepoPldgBondMgtEntityDto dto) {
        return  fitsRepoPldgBondMgtService.checkRepoPldgBondMgt(dto);
    }
}
