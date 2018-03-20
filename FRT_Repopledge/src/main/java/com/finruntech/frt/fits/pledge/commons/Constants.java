package com.finruntech.frt.fits.pledge.commons;

/**
 * Created by yinan.zhang on 2017/12/27.
 */
public final class Constants {
    /**
     * 序列
     */
    public static final String BPM_APRVFORM_SEQ = "BPM_APRVFORM_SEQ";//审批单
    public static final String DEALBOND_SEQ = "DEALBOND_SEQ";//成交单
    public static final String ORDER_SEQ = "ORDER_SEQ";//委托单
    public static final String POOLBOND_SEQ = "POOLBOND_SEQ";//债券池
    public static final String SETL_INST_SEQ = "SETL_INST_SEQ";//结算指令
    public static final String TRADE_BOND_SEQ = "TRADE_BOND_SEQ";//交易指令

    public static final String SAVE_PLEDGE_INST = "/pledge/savePledgeInst";//保存质押式指令单
    public static final String SUBMIT_PLEDGE_INST = "/pledge/submitPledgeInst";//保存并提交质押式指令单
    public static final String FIND_PLEDGE_INST = "/pledge/findPledgeInst";//查询质押式指令单
    public static final String UPDATE_PLEDGE_INST = "/pledge/updatePledgeInst";//修改质押式指令单
    public static final String QUERY_FITS_REPO_PLDG_BOND_MGT = "/pledge/queryRepoPldgBondMgt";//质押券
    public static final String FIND_REPO_PLDG_BONDMGT = "/pledge/findRepoPldgBondMgt";
    public static final String FIND_REPO_PLDG_ALL_BONDMGT = "/pledge/findRepoPldgAllBondMgt";

    public static final String FIND_REPO_ATTR = "/pledge/findRepoAttr";//查询回购品种
    public static final String QUERY_REPO_ATTR = "/pledge/queryRepoAttr";//查询回购品种
    public static final String UPDATE_FITS_REPO_ATTR = "/pledge/updateFitsRepoAttr";//更新回购品种
    public static final String SAVE_FITS_REPO_ATTR = "/pledge/saveFitsRepoAttr";//插入回购品种
    public static final String DELETE_FITS_REPO_ATTR = "/pledge/deleteFitsRepoAttr";//删除回购品种
    public static final String UPDATE_PLEDGE_CANCEL_INST = "/pledge/updatePledgeCancelInst";//取消提交
    public static final String SUBMIT_PLEDGE_INST_STATUS = "/pledge/submitPledgeInstStatus";//提交
    public static final String GET_REPO_PLDG_MGT = "/pledge/getRepoPldgMgt";//质押券查询
    public static final String FIND_REPO_PLDG_S_MGT = "/pledge/findRepoPldgSMgt";//逆回购质押券查询
    public static final String REPO_TERM_LIMIT = "/pledge/repoTermLimit";//回购天数/期限限制

    public static final String UPDATE_BANDMGT_AVAILABLE_INST = "/pledge/updateBandMgtAvailableInst";


    public static final String EXE_INST_QUERY = "/pledge/exeInstQuery";//指令执行->查询
    public static final String EXE_INVST_OPER = "/pledge/exeInvstOper";//指令执行
    public static final String ENTRUST_INVST_QUERY = "/pledge/entrustInvstQuery";//指令委托查询
    public static final String QRY_ORDER_LIST_BY_INVST_NUM= "/pledge/qryOrderlistByInvstNum";//据根据指令编号查询对应委托记录
    public static final String ADD_ORDER_INIT_INFO = "/pledge/addOrderInitInfo";//委托明细-初始化信息
    public static final String ORDER_OPER = "/pledge/orderOper";//委托操作
    public static final String PRINT_ORDER = "/pledge/printOrder";//打印委托单
    public static final String TRADE_ENTRYQRY_ORDER = "/pledge/tradeEntryQryOrder";//交易录入，委托查询
    public static final String TRADE_ENTRY_SUBMIT  = "/pledge/tradeEntryAubmit" ;//交易录入提交
    public static final String REPO_PLDG_MATCH_TRANS = "/api/v1/send/repopldgMatchTrans";//成交回报，匹配
    public static final String SETTLE_INST_QRY = "/pledge/settleInstQry";//结算指令查询
    public static final String MATURE_SETTLE_INST_QRY = "/pledge/matureSettleInstQry";//回购到期结算指令确认查询
    public static final String SETTLE_INST_COMFIRM = "/pledge/settleInstComfirm";//结算确认
    public static final String MATURE_AMOUNT_MODIFY = "/pledge/matureAmountModify";//到期结算金额调整
    public static final String MATCH_DEAL_LIST = "/pledge/matchDealList";//交易匹配->成交列表
    public static final String MATCH_ORDER_LIST = "/pledge/matchOrderList";//交易匹配->委托列表
    public static final String TRADE_ENTERDETAIL_QRY = "/pledge/tradeEnterDetailQry";//交易录入根据委托编号查询
    //approval
    public static final String REPO_START_PROCESS = "api/v1/pledge/repoStartProcess";//质押式回购提交审批;
    public static final String REPO_APPROVAL_QRY = "/pledge/repoApprovalQry";//质押式回购审批查询;
    public static final String REPO_APPROVAL = "/pledge/repoApproval";//质押式回购审批;
    public static final String REPO_RESTART_PROCESS = "/pledge/repoRestartProcess";//质押式回购重新提交
    public static final String REPO_CANCEL_PROCESS = "/pledge/repoCancelProcess";//质押式回购取消提交

    public static final String FIND_REPO_DEAL = "/pledge/findRepoDeal";

    public static final String NIB_REPOPLDG_QUERY="/pledge/nibRepoPldgQuery";//银行间回购查询



}
