package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CiticAction;
import cn.tyrone.payment.outreach.channel.common.AbstractXmlBaseRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 中信银行请求基类
 */
@Data
@SuperBuilder
public abstract class AbstractCiticBaseRequest extends AbstractXmlBaseRequest {

    /**
     * 登录名
     */
    protected String userName;

    /**
     * 账户
     */
    protected String accountNo;

    /**
     * 主体账号
     */
    protected String mainAccNo;

    /**
     * 对应请求代码
     */
    protected CiticAction citicAction;

    protected String actionProcessing() throws RuntimeException {
        String action = "<action>" + this.citicAction.getAction() + "</action>";
        return action;
    }

    @Override
    protected String statementProcessing() throws RuntimeException {

        String statement = "<?xml version=\"1.0\" encoding=\"GBK\"?>";

        return statement;
    }
}
