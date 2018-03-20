package com.finruntech.frt.fits.pledge.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 获取系统配置
 * Created by zws on 2017/5/31.
 */
@Entity
@IdClass(FitsSystemConfigEntity.FitsSystemConfigEntityPK.class)
@Table(name = "FRT_FITS_SYSTEMCONFIG")
public class FitsSystemConfigEntity implements Serializable{
	private static final long serialVersionUID = 8400341529960154337L;
	@Id
    @Column(name = "CONFIG_KEY")
    private String configKey;
    @Id
    @Column(name = "CONFIG_VALUE")
    private String configValue;
    @Column(name = "CONFIG_DESCRIPTION")
    private String configDescription;
    @Column(name = "CONFIG_USAGE")
    private String configUsage;
    @Column(name = "CONFIG_REMARK")
    private String configRemark;
    
    
    public String getConfigKey() {
		return configKey;
	}



	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}



	public String getConfigValue() {
		return configValue;
	}



	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}



	public String getConfigDescription() {
		return configDescription;
	}



	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}



	public String getConfigUsage() {
		return configUsage;
	}



	public void setConfigUsage(String configUsage) {
		this.configUsage = configUsage;
	}



	public String getConfigRemark() {
		return configRemark;
	}



	public void setConfigRemark(String configRemark) {
		this.configRemark = configRemark;
	}



	public static class FitsSystemConfigEntityPK implements Serializable {
        private static final long serialVersionUID = 8400341529960154337L;
    	@Id
        @Column(name = "CONFIG_KEY")
        private String configKey;
        @Id
        @Column(name = "CONFIG_VALUE")
        private String configValue;

        public FitsSystemConfigEntityPK() {
        }

        public FitsSystemConfigEntityPK(String configKey, String configValue) {
            this.configKey = configKey;
            this.configValue = configValue;
        }

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }


        public String getConfigKey() {
			return configKey;
		}

		public void setConfigKey(String configKey) {
			this.configKey = configKey;
		}

		public String getConfigValue() {
			return configValue;
		}

		public void setConfigValue(String configValue) {
			this.configValue = configValue;
		}

		@Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((configKey == null) ? 0 : configKey.hashCode());
            result = prime * result + ((configValue == null) ? 0 : configValue.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            FitsSystemConfigEntity.FitsSystemConfigEntityPK other = (FitsSystemConfigEntity.FitsSystemConfigEntityPK) obj;
            if (configKey == null) {
                if (other.configKey != null)
                    return false;
            } else if (!configKey.equals(other.configKey))
                return false;
            if (configValue == null) {
                if (other.configValue != null)
                    return false;
            } else if (!configValue.equals(other.configValue))
                return false;
            return true;
        }
    }
}
