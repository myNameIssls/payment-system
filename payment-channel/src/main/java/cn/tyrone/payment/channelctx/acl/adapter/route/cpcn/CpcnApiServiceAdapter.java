package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
import cn.tyrone.payment.channelctx.pl.AccountBalanceResponse;
import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
import cn.tyrone.payment.channelctx.pl.OpenAccountResponse;
import com.trz.netwk.api.trd.TrdT1005Request;
import com.trz.netwk.api.trd.TrdT1005Response;
import com.trz.netwk.api.trd.TrdT1031Request;
import com.trz.netwk.api.trd.TrdT1031Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中金支付API服务适配器
 */
@Service
public class CpcnApiServiceAdapter {

    @Autowired
    private CpcnApiService apiService;
    @Autowired
    private CpcnApiMessageConverter messageConverter;

    /**
     * 开户适配器
     * @param openAccountRequest
     * @return
     */
    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest){

        TrdT1031Request trdT1031Request = messageConverter.fromOpenAccountRequest(openAccountRequest);
        TrdT1031Response trdT1031Response = apiService.t1031(trdT1031Request);
        OpenAccountResponse openAccountResponse = messageConverter.toOpenAccountResponse(trdT1031Response);

        return openAccountResponse;
    }

    /**
     * 账户余额适配器
     * @param accountBalanceRequest
     * @return
     */
    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest) {
        TrdT1005Request trdT1005Request = messageConverter.fromAccountBalanceRequest(accountBalanceRequest);
        TrdT1005Response trdT1005Response = apiService.t1005(trdT1005Request);
        AccountBalanceResponse accountBalanceResponse = messageConverter.toAccountBalanceResponse(trdT1005Response);
        return accountBalanceResponse;
    }


}
