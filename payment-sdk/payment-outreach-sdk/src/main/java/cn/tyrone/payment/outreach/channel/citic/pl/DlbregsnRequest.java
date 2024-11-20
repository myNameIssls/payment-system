package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 会员注册
 */
@Data
@SuperBuilder
public class DlbregsnRequest extends AbstractCiticBaseRequest {

    /**
     * 应用系统char(1)， 2：B2B电子商务；3：投标保证金
     */
    private AppFlag appFlag;

    /**
     * 资金分簿生成方式char(1) ，0：自动输入 ；1：手动生成
     */
    private AccGenType accGenType;

    /**
     * 资金分簿编号尾号
     * 规则：accGenType == 1 必输, accGenType == 0 非必输
     */
    private String subAccNo;

    /**
     * 资金分簿名称
     * 规则：appFlag == 2,不允许为空，其值必须是客户名称
     * appFlag == 其它值，允许为空
     */
    private String subAccNm;

    /**
     * 资金分簿类型
     */
    private AccType accType;

    /**
     * 是否计算利息标志
     * 规则：当appFlag为3时，是否计算利息标志必须为0
     */
    private CalInterestFlag calInterestFlag;

    /**
     * 默认计息利率 decimal(9.7)
     * calInterestFlag为 0时，可空；appFlag为3时，必须为0
     */
    private String interestRate;

    /**
     * 是否允许透支
     * appFlag为3时，必须为0
     */
    private OverFlag overFlag;

    /**
     * 透支额度decimal(15.2)，当overFlag为 0时，可空；appFlag为3时，必须为空
     */
    private String overAmt;

    /**
     * 透支利率decimal(9.7)，当overFlag为 0时，可空；appFlag为3时，必须为空
     */
    private String overRate;

    /**
     * 自动分配转账手续费标char(1)，0：否；1：是；
     * appFlag为3时，必须为0
     */
    private AutoAssignInterestFlag autoAssignInterestFlag;

    /**
     * 自动分配转账手续费标char(1)，0：否；1：是；
     * appFlag为3时，必须为0
     */
    private AutoAssignTranFeeFlag autoAssignTranFeeFlag;

    /**
     * 手续费收取方式 char(1)，0：不收取；1：实时收取；2：月末累计；
     * appFlag为3时，必须为0
     */
    private FeeType feeType;

    /**
     * 实名制更换char(1) ，0：账户名与账号全不换；1：账户名与账号全换；2：换账户名；3：换账号；
     * appFlag为3时，必须为0
     */
    private RealNameParm realNameParm;

    /**
     * 资金分簿凭证打印更换 char(1)，0：全部显示；1：显示资金分簿名和账号；2：显示实体账户名和账号；
     * 3：显示资金分簿名和实体账号；4：显示实体账户名和资金分簿编号；
     * appFlag为3时，必须为0
     */
    private SubAccPrintParm subAccPrintParm;

    /**
     * 会员确认中心
     */
    private String mngNode;
    /**
     * 虚拟客户名称
     */
    private String vtlCustNm;
    /**
     * 法人名称
     */
    private String legalPersonNm;
    /**
     * 客户证件类型
     */
    private CustCertType custCertType;
    /**
     * 客户证件号码
     */
    private String custCertNo;
    /**
     * 所属机构
     */
    private String branch;
    /**
     * 通讯地址 varchar(152)
     */
    private String commAddress;

    /**
     * 联系人数据
     */
    private List<VilcstData> vilcstDataList;


    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("mainAccNo", this.mainAccNo, Boolean.TRUE));
        xml.append(super.elementProcessing("appFlag", this.appFlag.getAppFlag(), Boolean.TRUE));
        xml.append(super.elementProcessing("accGenType", this.accGenType.getAccGenType(), Boolean.TRUE));

        boolean subAccNoB = this.accGenType.equals(AccGenType.AUTO_INPUT) ? false : true;
        xml.append(super.elementProcessing("subAccNo", this.subAccNo, subAccNoB));

        boolean subAccNmB = this.appFlag.equals(AppFlag.B2B) ? true : false;
        xml.append(super.elementProcessing("subAccNm", this.subAccNm, subAccNmB));

        xml.append(super.elementProcessing("accType", this.accType.getAccType(), Boolean.TRUE));

        if (this.appFlag.equals(AppFlag.BID_SECURITY)) {
            this.calInterestFlag = CalInterestFlag.ZERO;
            this.interestRate = "0";
            this.overFlag = OverFlag.ZERO;
        }
        xml.append(super.elementProcessing("calInterestFlag", this.calInterestFlag.getCalInterestFlag(), Boolean.TRUE));

        boolean interestRateB = this.calInterestFlag.equals(CalInterestFlag.ZERO) ? false : true;
        xml.append(super.elementProcessing("interestRate", this.interestRate, interestRateB));
        xml.append(super.elementProcessing("overFlag", this.overFlag.getOverFlag(), Boolean.TRUE));
        boolean overAmtB = this.overFlag.equals(OverFlag.ZERO) ? false : true;
        overAmtB = this.appFlag.equals(AppFlag.BID_SECURITY) ? false : true;
        xml.append(super.elementProcessing("overAmt", this.overAmt, overAmtB));


        boolean overRateB = this.overFlag.equals(OverFlag.ZERO) ? false : true;
        overRateB = this.appFlag.equals(AppFlag.BID_SECURITY) ? false : true;
        xml.append(super.elementProcessing("overRate", this.overRate, overRateB));

        if (this.appFlag.equals(AppFlag.BID_SECURITY)) {
            this.autoAssignInterestFlag = AutoAssignInterestFlag.ZERO;
            this.autoAssignTranFeeFlag = AutoAssignTranFeeFlag.ZERO;
            this.feeType = FeeType.ZERO;
            this.realNameParm = RealNameParm.ZERO;
            this.subAccPrintParm = SubAccPrintParm.ZERO;
        }
        xml.append(super.elementProcessing("autoAssignTranFeeFlag", this.autoAssignTranFeeFlag.getAutoAssignInterestFlag(), Boolean.TRUE));
        xml.append(super.elementProcessing("feeType", this.feeType.getFeeType(), Boolean.TRUE));
        xml.append(super.elementProcessing("realNameParm", this.realNameParm.getRealNameParm(), Boolean.TRUE));
        xml.append(super.elementProcessing("subAccPrintParm", this.subAccPrintParm.getSubAccPrintParm(), Boolean.TRUE));

        xml.append(super.elementProcessing("mngNode", this.mngNode, Boolean.TRUE));
        xml.append(super.elementProcessing("vtlCustNm", this.vtlCustNm, Boolean.TRUE));
        xml.append(super.elementProcessing("legalPersonNm", this.legalPersonNm, Boolean.TRUE));
        xml.append(super.elementProcessing("custCertType", this.custCertType.getCustCertType(), Boolean.TRUE));
        xml.append(super.elementProcessing("custCertNo", this.custCertNo, Boolean.TRUE));
        xml.append(super.elementProcessing("branch", this.branch, Boolean.TRUE));
        xml.append(super.elementProcessing("commAddress", this.commAddress, Boolean.TRUE));

        xml.append("<list name=\"VilcstDataList\">");

        vilcstDataList.forEach(vilcstData -> {
            xml.append(vilcstData.listProcessing());
        });

        xml.append("</list>");
        xml.append("</stream>");

        return xml.toString();
    }

}
