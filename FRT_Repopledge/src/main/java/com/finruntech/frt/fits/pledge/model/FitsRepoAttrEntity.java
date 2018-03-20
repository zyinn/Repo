package com.finruntech.frt.fits.pledge.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yinan.zhang on 2018/1/2.
 */
@Entity
@Table(name = "FRT_FITS_REPOATTR")
@Setter
@Getter
@IdClass(FitsRepoAttrEntity.FitsRepoAttrEntityPK.class)
public class FitsRepoAttrEntity {
    @Id
    @Column(name = "RA_MARKET")
    private String raMarket;//回购市场
    @Id
    @Column(name = "RA_REPO_CODE")
    private String raRepoCode;// 回购代码
    @Column(name = "RA_REPO_NAME")
    private String raRepoName;//回购名称
    @Column(name = "RA_REPO_TYPE")
    private String raRepoType;// 回购类型(买断式/质押式)
    @Column(name = "RA_REPO_TERM")
    private String raRepoTerm;//回购天数/期限
    @Column(name = "RA_REPO_UNIT")
    private String raRepoUnit;//交易单位（手/张）
    @Column(name = "RA_REPO_AMOUNT")
    private long raRepoAmount;// 每单位数量/金额
    @Column(name = "RA_MANUALINPUT")
    private String raManualInput;//是否手工录入(0:万得导入可被覆盖；1:手工维护数据并可以被覆盖；2:手工维护并不可被覆盖)

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class FitsRepoAttrEntityPK implements Serializable {
        private static final long serialVersionUID = -9031512642696449018L;
        @Id
        @Column(name = "RA_MARKET", nullable = false)
        private String raMarket;//回购市场
        @Id
        @Column(name = "RA_REPO_CODE", nullable = false)
        private String raRepoCode;// 回购代码
    }
}
