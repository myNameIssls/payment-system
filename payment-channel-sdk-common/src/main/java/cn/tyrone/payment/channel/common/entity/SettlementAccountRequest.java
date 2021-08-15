package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.BankAccountType;
import cn.tyrone.payment.channel.common.enums.CertificateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 结算账户请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementAccountRequest extends CommonRequest {

    /**
     * 账户
     */
    private String account;

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 银行账号开户名称
     */
    private String bankAccountName;

    /**
     * 结算账户类型
     */
    private BankAccountType bankAccountType;

    /**
     * 证件类型
     */
    private CertificateType certificateType;

    /**
     * 证件号码
     */
    private String certificateNum;

    /**
     * 银行编码
     */
    private String bankCode;

    /**
     * 银行支行大额行号
     */
    private String bankBranchCode;

    /**
     * 银行支行名称
     */
    private String bankBranchName;

    /**
     * 开户行省份名称
     */
    private String bankBranchProNm;

    /**
     * 开户行省份编码
     */
    private String bankBranchProCd;

    /**
     * 开户行城市名称
     */
    private String bankBranchCtNm;

    /**
     * 开户行城市编码
     */
    private String bankBranchCtCd;

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 业务类型
     */
    public enum BusinessType {

        ONE("1", "绑定"),
        TWO("2", "变更"),
        THREE("3", "删除"),
        ;

        public String type;

        public String describe;

        BusinessType(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }
    }


}
