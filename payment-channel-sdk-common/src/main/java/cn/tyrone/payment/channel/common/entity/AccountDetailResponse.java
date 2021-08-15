package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 账户明细应答对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class AccountDetailResponse extends CommonResponse {

    /**
     * 账户（虚拟账户、共管账户、银行账户等）
     */
    private String account;

    /**
     * 户名（虚拟账户、共管账户、银行账户等）
     */
    private String accountName;

    /**
     * 总记录数
     */
    private int totalRecords;

    /**
     * 返回记录数
     */
    private int returnRecords;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 账户明细
     */
    private List<AccountDetail> accountDetails;

}
