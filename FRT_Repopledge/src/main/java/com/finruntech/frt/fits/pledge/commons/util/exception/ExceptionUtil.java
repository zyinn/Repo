package com.finruntech.frt.fits.pledge.commons.util.exception;

/**
 * 异常信息
 * Created by yinan.zhang on 2017/5/31.
 */
public enum  ExceptionUtil implements ExceptionType{
    EerrInfo("FITS.ERROR.E1000",""),
    DATABASE_ERROR("FITS.ERROR.E1001","数据库错误"),
    WEB_SOCKET_ERROR("FITS.ERROR.E3001","消息错误"),
    QM_STATUS_ERROR("FITS.ERROR.E4001","QM总线错误"),
    AUTHORIZE_INVALID("FITS.ERROR.E8888","认证失败"),
    USER_PASSWORD_ERROR("FITS.ERROR.E8001","密码错误"),
    USER_AUTH_ERROR("FITS.ERROR.E8002","该用户无此权限"),
    E5001("FITS.ERROR.E5001", "指令可用余额还未生成，无法委托"),
    E5002("FITS.ERROR.E5002","委托金额大于指令可用余额，无法继续委托!"),
    E5003("FITS.ERROR.E5003","匹配金额大于委托可用余额，无法匹配!"),
    E5004("FITS.ERROR.E5004","未找到匹配记录!"),
    E5005("FITS.ERROR.E5005","匹配到多条记录!"),
    E5006("FITS.ERROR.E5006","成交数量总和大于委托数量!"),
    E5007("FITS.ERROR.E5007","成交金额大于委托单剩余额度!"),
    E5008("FITS.ERROR.E5008","委托单已全部成交，无法继续匹配!"),
    E5009("FITS.ERROR.E5009","委托单已有成交记录，无法取消委托!"),
    E5010("FITS.ERROR.E5010","成交数量小于或等于0，交易匹配失败!"),
    E5011("FITS.ERROR.E5011","该笔成交的结算指令的确认状态为‘已确认',无法取消匹配!"),
    E5012("FITS.ERROR.E5012","该笔成交的结算指令的结算状态为‘已结算',无法取消匹配!"),
    E5013("FITS.ERROR.E5013","不能删除用户自己!"),
    E5014("FITS.ERROR.E5014","用户名已存在!"),
    E5015("FITS.ERROR.E5015","执行失败，没有执行权限!"),
    E5016("FITS.ERROR.E5016","委托失败，没有委托权限!"),
    E5017("FITS.ERROR.E5017","取消委托失败，没有取消权限!"),
    E5018("FITS.ERROR.E5018","计算返回错误!"),
    E5019("FITS.ERROR.E5019","委托失败，委托面额不能小于或等于0 !"),
    E5020("FITS.ERROR.E5020","角色名已存在!"),
    E5021("FITS.ERROR.E5021","债券已入交易池或禁止池，不能出总池!"),
    E5022("FITS.ERROR.E5022","调用流程引擎错误!"),
    E5023("FITS_ERROR_E5023","委托失败,历史数据不能委托撤单!"),
    E6001("FITS_ERROR_E6001","指令单错误"),
    E6002("FITS_ERROR_E6002","质押数量超过了可用数量"),
    OTHER("FITS.ERROR.E9999","其他运行错误");

    ExceptionUtil(String code,String info) {
        this.code = code;
        this.errorInfoCN = info;
    }

    private String code;
    private String errorInfoCN;

    @Override
    public String getExceptionCode() {
        return code;
    }

    @Override
    public String getExceptionInfoCN() {
        return errorInfoCN;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorInfoCN() {
        return errorInfoCN;
    }

    public void setErrorInfoCN(String errorInfoCN) {
        this.errorInfoCN = errorInfoCN;
    }
}
