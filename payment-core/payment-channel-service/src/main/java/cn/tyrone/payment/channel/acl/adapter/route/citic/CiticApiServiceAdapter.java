package cn.tyrone.payment.channel.acl.adapter.route.citic;


import cn.tyrone.payment.channel.pl.AccountBalanceRequest;
import cn.tyrone.payment.channel.pl.AccountBalanceResponse;
import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrRequest;
import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrResponse;
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
