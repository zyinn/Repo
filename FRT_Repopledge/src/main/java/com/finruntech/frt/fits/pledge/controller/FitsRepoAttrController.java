package com.finruntech.frt.fits.pledge.controller;

import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.converter.FitsPledgeInstConverter;
import com.finruntech.frt.fits.pledge.model.FitsRepoAttrEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoAttrDto;
import com.finruntech.frt.fits.pledge.service.FitsRepoAttrService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 质押式回购产品
 * Created by yinan.zhang on 2018/1/2.
 */
@RestController
@CrossOrigin
public class FitsRepoAttrController {
    @Autowired
    private FitsRepoAttrService fitsRepoAttrService;
    @Autowired
    private FitsPledgeInstConverter pledgeInstConverter;

    /**
     * 根据FitsRepoAttrDto查询质押式回购产品
     * @param fitsRepoAttrDto FitsRepoAttrDto类
     * @return List FitsRepoAttrEntity
     */
    @RequestMapping(value = Constants.FIND_REPO_ATTR)
    public ResponseEntity<?> fitsRepoAttrController(@RequestBody FitsRepoAttrDto fitsRepoAttrDto) {
        FitsRepoAttrEntity entity = pledgeInstConverter.fitsRepoAttrConverter(fitsRepoAttrDto);
        return ResponseEntity.ok().body(fitsRepoAttrService.fitsRepoAttrEntityList(entity));
    }

    /**
     * 根据回购市场/回购代码/回购类型(买断式/质押式) 查询质押式回购产品
     * @param fitsRepoAttrDto FitsRepoAttrDto类
     * @return list
     */
    @RequestMapping(value = Constants.QUERY_REPO_ATTR)
    public ResponseEntity<?> queryFitsRepoAttrController(@RequestBody FitsRepoAttrDto fitsRepoAttrDto) {
        PageInfo<?> pageInfo = fitsRepoAttrService.queryFitsRepoAttrEntityList(fitsRepoAttrDto);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 新增质押式回购产品
     * @param attrEntity
     * @return int
     */
    @RequestMapping(value = Constants.SAVE_FITS_REPO_ATTR)
    public ResponseEntity<?> saveFitsRepoAttrController(@RequestBody FitsRepoAttrEntity attrEntity) {
        return ResponseEntity.ok().body(fitsRepoAttrService.saveFitsRepoAttrEntity(attrEntity));
    }

    /**
     * 修改质押式回购产品
     * @param attrEntity FitsRepoAttrEntity 质押式回购产品
     * @return int
     */
    @RequestMapping(value = Constants.UPDATE_FITS_REPO_ATTR)
    public ResponseEntity<?> updateFitsRepoAttrController(@RequestBody FitsRepoAttrEntity attrEntity) {
        return ResponseEntity.ok().body(fitsRepoAttrService.updateFitsRepoAttrEntity(attrEntity));
    }

    /**
     * 删除质押式回购产品
     * @param attrEntity FitsRepoAttrEntity 质押式回购产品
     * @return int
     */
    @RequestMapping(value = Constants.DELETE_FITS_REPO_ATTR)
    public ResponseEntity<?> deleteFitsRepoAttrController(@RequestBody FitsRepoAttrEntity attrEntity) {
        return ResponseEntity.ok().body(fitsRepoAttrService.deleteFitsRepoAttrEntity(attrEntity));
    }
}
