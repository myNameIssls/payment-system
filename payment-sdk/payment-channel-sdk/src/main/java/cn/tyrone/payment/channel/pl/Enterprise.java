package cn.tyrone.payment.channel.pl;

import cn.tyrone.payment.channel.enums.EnterpriseScale;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Enterprise {

    /**
     * 企业名称
     */
    private String name;

    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;

    /**
     * 统一社会信用代码开始日期
     */
    private String unifiedSocialCreditCodeBeginDate;

    /**
     * 统一社会信用代码到期日期
     */
    private String unifiedSocialCreditCodeEndDate;

    /**
     * 是否长期
     */
    private Boolean ifLongTerm;

    /**
     * 统一社会信用代码证件照
     */
    private String unifiedSocialCreditCodeUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 地址所属城市代码
     */
    private String cityCode;

    /**
     * 行业代码 非必填
     */
    private String industryCode;

    /**
     * 行业代码 非必填
     */
    private EnterpriseScale enterpriseScale;

    /**
     * 开户许可证核准号
     */
    private String licenceCMII;

    /**
     * 注册资本
     */
    private String registeredCapital;

    /**
     * 经营范围
     */
    private String businessScope;


}
