package cn.tyrone.payment.channel.domain.adapter;


import cn.tyrone.payment.channel.common.entity.OpenAccountRequest;
import cn.tyrone.payment.channel.common.entity.OpenAccountResponse;
import cn.tyrone.payment.channel.common.entity.WithdrawRequest;
import cn.tyrone.payment.channel.common.entity.WithdrawResponse;

/**
 * 虚拟账户支付服务适配器接口
 */
public interface IVirtualAccountPaymentServiceAdapter extends ICommunalPaymentServiceAdapter {

    /**
     * 开户
     * @param request
     * @return
     */
    OpenAccountResponse openAccount(OpenAccountRequest request);

    /**
     * 出金提现
     * @param request
     * @return
     */
    WithdrawResponse withdraw(WithdrawRequest request);

}
