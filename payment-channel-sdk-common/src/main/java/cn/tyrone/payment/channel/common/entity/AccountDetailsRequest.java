package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * 账户明细请求对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsRequest extends CommonRequest {

    /**
     * 账户（虚拟账户、共管账户、银行账户等）
     */
    private String account;

    /**
     * 起始日期
     */
    private LocalDate startDate;

    /**
     * 终止日期
     */
    private LocalDate endDate;

    /**
     * 请求记录条数
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 起始记录号
     */
    private int startNum;

    /**
     * 原平台流水号
     */
    private String originalPlatformSerialNumber;

    /**
     * 原渠道流水号
     */
    private String originalChannelSerialNumber;

    /**
     * 判断是否是查询当日记录
     * @return
     */
    public boolean ifToday(){

        if (this.startDate == null && this.endDate == null) {
            return true;
        }

        return false;

    }


}
