package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.FitsRepoAttrEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoAttrDto;
import com.finruntech.frt.fits.pledge.repository.FitsRepoAttrMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 质押式回购产品 service
 * Created by yinan.zhang on 2018/1/2.
 */
@Service
public class FitsRepoAttrService {
    @Autowired
    private FitsRepoAttrMapper fitsRepoAttrMapper;

    /**
     * 根据FitsRepoAttrEntity 查询质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return list FitsRepoAttrEntity
     */
    public List<FitsRepoAttrEntity> fitsRepoAttrEntityList(FitsRepoAttrEntity attrEntity){
       return fitsRepoAttrMapper.findFitsRepoAttrEntity(attrEntity);
    }

    /**
     * 根据回购市场/回购代码/回购类型(买断式/质押式) 查询质押式回购产品
     * @param attrEntity FitsRepoAttrDto
     * @return list
     */
    public PageInfo<?> queryFitsRepoAttrEntityList(FitsRepoAttrDto attrEntity){
        Map<String, String> map = new HashMap<>();
        map.put("raMarket", attrEntity.getRaMarket());
        map.put("raRepoCode", "%"+attrEntity.getRaRepoCode()+"%");
        map.put("raRepoType", attrEntity.getRaRepoType().equals("") ? null : attrEntity.getRaRepoType());
        StringBuilder sb = new StringBuilder();
        sb.append(attrEntity.getOrderColumn() + " " + attrEntity.getOrderBy());
        PageHelper.startPage(attrEntity.getPageNum(), attrEntity.getPageSize(), sb.toString());
        return new PageInfo<>(fitsRepoAttrMapper.queryFitsRepoAttrEntity(map));
    }

    /**
     * 新增质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return 新增成功数量
     */
    public int saveFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity){
        return fitsRepoAttrMapper.saveFitsRepoAttrEntity(attrEntity);
    }

    /**
     * 删除质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return 修改成功数量
     */
    public int updateFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity){
        return fitsRepoAttrMapper.updateFitsRepoAttrEntity(attrEntity);
    }

    /**
     * 删除质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return 删除成功数量
     */
    public int deleteFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity){
        return fitsRepoAttrMapper.deleteFitsRepoAttrEntity(attrEntity);
    }

}
