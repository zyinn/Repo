package com.finruntech.frt.fits.pledge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by lenovo on 2017/10/31.
 */
@XmlRootElement(name = "visible_attrs")
public class visible_attrs {
    private BigDecimal repoRateDeviation;

    public visible_attrs() {
    }

    public visible_attrs(BigDecimal repoRateDeviation) {
        this.repoRateDeviation = repoRateDeviation;
    }

    public BigDecimal getRepoRateDeviation() {
        return repoRateDeviation;
    }

    public void setRepoRateDeviation(BigDecimal repoRateDeviation) {
        this.repoRateDeviation = repoRateDeviation;
    }
}
