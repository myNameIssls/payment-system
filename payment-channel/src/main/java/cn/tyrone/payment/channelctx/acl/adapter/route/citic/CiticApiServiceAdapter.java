package cn.tyrone.payment.channelctx.acl.adapter.route.citic;

import cn.tyrone.payment.channelctx.acl.adapter.route.citic.model.DlsbalqrRequest;
import cn.tyrone.payment.channelctx.acl.adapter.route.citic.model.DlsbalqrResponse;
import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
import cn.tyrone.payment.channelctx.pl.AccountBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中金支付API服务适配器
 */
@Service
public class CiticApiServiceAdapter {

    @Autowired
    private CiticApiService apiService;
    @Autowired
    private CiticApiMessageConverter messageConverter;

    /**
     * 账户余额适配器
     * @param accountBalanceRequest
     * @return
     */
    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest) {

        DlsbalqrRequest request = messageConverter.fromAccountBalanceRequest(accountBalanceRequest);
        DlsbalqrResponse dlsbalqrResponse = apiService.dlsbalqr(request);
        AccountBalanceResponse accountBalanceResponse = messageConverter.toAccountBalanceResponse(dlsbalqrResponse);
        return accountBalanceResponse;
    }


}
