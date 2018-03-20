package com.finruntech.frt.fits.pledge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.annotation.FitsApproval;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.converter.FitsPledgeInstConverter;
import com.finruntech.frt.fits.pledge.model.FitsAccPortfolioSecuEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgInstEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.dto.*;
import com.finruntech.frt.fits.pledge.service.FitsAccPortfolioSecuService;
import com.finruntech.frt.fits.pledge.service.FitsRepoPldgBondMgtService;
import com.finruntech.frt.fits.pledge.service.FitsRepoPldgInstService;
import com.finruntech.frt.fits.pledge.service.FitsRepoPldgMgtService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 投资指令
 * Created by yinan.zhang on 2017/12/27.
 */
@RestController
@CrossOrigin
public class FitsRepoPledgeInstController<T> {
    @Autowired
    private FitsRepoPldgInstService fitsPledgeInstService;
    @Autowired
    private FitsPledgeInstConverter pledgeInstConverter;
    @Autowired
    private FitsRepoPldgBondMgtService repoPldgBondMgtService;
    @Autowired
    private FitsAccPortfolioSecuService accPortfolioSecuService;
    @Autowired
    private FitsRepoPldgMgtService repoPldgMgtService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 保存投资指令
     * @param fitsRepoPldgInstDto 指令单类
     * @return int
     */
    @RequestMapping(value = Constants.SAVE_PLEDGE_INST)
    public ResponseEntity<?> savePledgeInst(@Valid @RequestBody FitsRepoPldgInstDto fitsRepoPldgInstDto) {
        FitsRepoPldgInstEntity entity = (FitsRepoPldgInstEntity) pledgeInstConverter.pledgeInstDtoConverter(fitsRepoPldgInstDto);
        FitsAccPortfolioSecuEntity secuEntity = accPortfolioSecuService.findFirstByPsAcctClassAndPsCashNum(entity.getFAccountingType(), entity.getFPortfAcctSecu());
        FitsRepoPldgInstEntity instEntity = (FitsRepoPldgInstEntity) pledgeInstConverter.pledgeInstConverterBySecuEntity(entity, secuEntity);
        FitsRepoPldgQueryInstDto dto = pledgeInstConverter.repoPldgQueryInstDtoConverter(instEntity);
        return ResponseEntity.ok().body(fitsPledgeInstService.savePledgeInst(instEntity,dto,fitsRepoPldgInstDto.getPldgMgtList()));
    }

    /**
     * 保存并提交投资指令
     * @param fitsRepoPldgInstDto 指令单类
     * @return int
     */
    @RequestMapping(value = Constants.SUBMIT_PLEDGE_INST)
    public ResponseEntity<?> submitPledgeInst(@Valid @RequestBody FitsRepoPldgInstDto fitsRepoPldgInstDto) {
        FitsRepoPldgInstEntity entity = (FitsRepoPldgInstEntity) pledgeInstConverter.pledgeInstDtoConverter(fitsRepoPldgInstDto);
        FitsAccPortfolioSecuEntity secuEntity = accPortfolioSecuService.findFirstByPsAcctClassAndPsCashNum(entity.getFAccountingType(), entity.getFPortfAcctSecu());
        FitsRepoPldgInstEntity instEntity = (FitsRepoPldgInstEntity) pledgeInstConverter.pledgeInstConverterBySecuEntity(entity, secuEntity);
        FitsRepoPldgQueryInstDto dto = pledgeInstConverter.repoPldgQueryInstDtoConverter(instEntity);
        return ResponseEntity.ok().body(fitsPledgeInstService.submitPledgeInst(instEntity,dto,fitsRepoPldgInstDto));
    }

    /**
     * 修改投资指令单
     * @param dto FitsRepoPldgInstDto指令单类
     * @return int
     */
    @RequestMapping(value = Constants.UPDATE_PLEDGE_INST)
    public ResponseEntity<?> updateFitsPledgeInst(@Valid @RequestBody FitsRepoPldgInstDto dto) {
        FitsRepoPldgInstEntity pldgInstEntity = (FitsRepoPldgInstEntity) pledgeInstConverter.pledgeInstEntityDtoConverter(dto);
        FitsAccPortfolioSecuEntity secuEntity = accPortfolioSecuService.findFirstByPsAcctClassAndPsCashNum(pldgInstEntity.getFAccountingType(), pldgInstEntity.getFPortfAcctSecu());
        FitsRepoPldgInstEntity instEntity = (FitsRepoPldgInstEntity) pledgeInstConverter.pledgeInstEntityConverterBySecuEntity(pldgInstEntity, secuEntity);
        List<FitsRepoPldgMgtEntity> list=pledgeInstConverter.getPldgMgtList(instEntity, dto.getPldgMgtList());
        return ResponseEntity.ok().body(fitsPledgeInstService.updateFitsPledgeInst(instEntity,list));
    }

    /**
     * 投资指令单撤销，提交撤销
     * @param dto FitsRepoCancelPldgInstDto
     * @return int
     */
    @RequestMapping(value = Constants.UPDATE_PLEDGE_CANCEL_INST)
    public ResponseEntity<?> updatePledgeCancelInst(@RequestBody @FitsApproval(flag = "3") FitsRepoCancelPldgInstDto dto) {
        return ResponseEntity.ok().body(fitsPledgeInstService.updatePledgeCancelInst(dto));
    }

    /**
     * 提交投资指令单
     * @param dto FitsRepoCancelPldgInstDto
     * @return int
     */
    @RequestMapping(value = Constants.SUBMIT_PLEDGE_INST_STATUS)
    public ResponseEntity<?> submitPledgeInstStatus(@RequestBody @FitsApproval FitsRepoCancelPldgInstDto dto) {
        return ResponseEntity.ok().body(fitsPledgeInstService.submitPledgeInstStatus(dto));
    }
    /**
     * 查询可用券
     * @param fitsRepoPldgBondMgt
     * @return list
     */
    @RequestMapping(value = Constants.QUERY_FITS_REPO_PLDG_BOND_MGT)
    public ResponseEntity<?> queryFitsRepoPldgBondMgt(@RequestBody String fitsRepoPldgBondMgt) {
        FitsRepoPldgBondMgtDto entity = pledgeInstConverter.fitsRepoPldgBondMgtDtoConverter(fitsRepoPldgBondMgt);
        PageInfo<?> pageInfo = repoPldgBondMgtService.queryFitsRepoPldgBondMgt(entity);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    @RequestMapping(value = Constants.FIND_REPO_PLDG_BONDMGT)
    public ResponseEntity<?> findRepoPldgBondMgt(@RequestBody String fitsRepoPldgBondMgt) {
        FitsRepoPldgBondMgtDto entity = pledgeInstConverter.fitsRepoPldgBondMgtDtoConverter(fitsRepoPldgBondMgt);
        PageInfo<?> pageInfo = repoPldgBondMgtService.findRepoPldgBondMgt(entity);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    @RequestMapping(value = Constants.FIND_REPO_PLDG_ALL_BONDMGT)
    public ResponseEntity<?> findRepoPldgAllBondMgt(@RequestBody String fitsRepoPldgBondMgt) {
        FitsRepoPldgBondMgtDto entity = pledgeInstConverter.fitsRepoPldgBondMgtDtoConverter(fitsRepoPldgBondMgt);
        PageInfo<?> pageInfo = repoPldgBondMgtService.findRepoPldgAllBondMgt(entity);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 根据FitsRepoPldgQueryInstDto查询投资指令单
     * @param dto FitsRepoPldgQueryInstDto
     * @return list
     */
    @RequestMapping(value = Constants.FIND_PLEDGE_INST)
    public ResponseEntity<?> queryPledgeInst(@RequestBody FitsRepoPldgQueryInstDto dto) {
        PageInfo<T> pageInfo = fitsPledgeInstService.queryPledgeInst(dto);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 修改可用券
     * @return int
     */
    @RequestMapping(value = Constants.UPDATE_BANDMGT_AVAILABLE_INST)
    public ResponseEntity<?> updateBandMgtAvailableInst() {
        try {
            logger.info("updateBandMgtAvailableInst start...");
            fitsPledgeInstService.updateBandMgtAvailableInst();
            return ResponseEntity.ok().body(200);
        } catch (Exception e) {
            return ResponseEntity.ok().body(500);
        }
    }

    @RequestMapping(value = Constants.GET_REPO_PLDG_MGT)
    public ResponseEntity<?> getRepoPldgMgt(@RequestBody @FitsApproval FitsRepoPldgMgtDto dto) {
        List list = repoPldgMgtService.findRepoPldgMgtEntity(dto);
        List listJson = new ArrayList();
        list.forEach(entity->{
            Object o = JSON.toJSON(entity);
            listJson.add(o);
        });
        return ResponseEntity.ok().body(listJson);
    }

    @RequestMapping(value = Constants.FIND_REPO_PLDG_S_MGT)
    public ResponseEntity<?> findRepoPldgSMgt(@RequestBody @FitsApproval FitsRepoPldgMgtDto dto) {
        List list = repoPldgMgtService.findRepoPldgSMgt(dto);
        List listJson = new ArrayList();
        list.forEach(entity->{
            Object o = JSON.toJSON(entity);
            listJson.add(o);
        });
        return ResponseEntity.ok().body(listJson);
    }


    @RequestMapping(value = Constants.REPO_TERM_LIMIT)
    public ResponseEntity<?> repoTermLimit(@RequestBody String param) {
        return ResponseEntity.ok().body(fitsPledgeInstService.repoTermLimitDays());
    }

}
