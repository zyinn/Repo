package com.finruntech.frt.fits.pledge.converter;

import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgOrdDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/15.
 */
@Component
public class FitsRepoPldgMgtConverter {

    /**
     * 质押券信息转换
     * @param pldgMgtList
     * @param subJectType
     * @param subJectNum
     * @return
     */
    public List<FitsRepoPldgMgtEntity> repoPldgMgtConverter(List<FitsRepoPldgMgtEntity> pldgMgtList, String subJectType,
                                                           String subJectNum, String hostSecu, String hostCash){
        List<FitsRepoPldgMgtEntity> list = new ArrayList<>();
        pldgMgtList.forEach(entity->{
            FitsRepoPldgMgtEntity entity1 = new FitsRepoPldgMgtEntity();
            BeanUtils.copyProperties(entity, entity1);
            entity1.setSubJectType(subJectType);
            entity1.setSubJectNum(subJectNum);
            entity1.setPHostSecu(hostSecu);//托管证券账号
            entity1.setPHostCash(hostCash);//托管资金账号
            list.add(entity1);
        });
        return list;
    }

}
