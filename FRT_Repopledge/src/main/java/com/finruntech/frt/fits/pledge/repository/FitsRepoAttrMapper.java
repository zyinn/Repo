package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.FitsRepoAttrEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 质押式回购产品 mapper
 * Created by yinan.zhang on 2018/1/2.
 */
@Repository("fitsRepoAttrMapper")
public interface FitsRepoAttrMapper {
    /**
     *查询质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return list FitsRepoAttrEntity
     */
    List<FitsRepoAttrEntity> findFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity);

    /**
     * 查询质押式回购产品
     * @param map map
     * @return list FitsRepoAttrEntity
     */
    List<FitsRepoAttrEntity> queryFitsRepoAttrEntity(Map<String, String> map);

    /**
     * 新增质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return 影响行数
     */
    int saveFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity);

    /**
     * 修改质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return 影响行数
     */
    int updateFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity);

    /**
     * 删除质押式回购产品
     * @param attrEntity FitsRepoAttrEntity
     * @return 影响行数
     */
    int deleteFitsRepoAttrEntity(FitsRepoAttrEntity attrEntity);
}
