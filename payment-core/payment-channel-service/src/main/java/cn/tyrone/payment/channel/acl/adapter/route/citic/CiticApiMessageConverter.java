package cn.tyrone.payment.channel.acl.adapter.route.citic;


import cn.tyrone.payment.channel.pl.AccountBalanceRequest;
import cn.tyrone.payment.channel.pl.AccountBalanceResponse;
import cn.tyrone.payment.outreach.channel.citic.enums.CiticAction;
import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrRequest;
import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrResponse;
import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrResponseUserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Component
public class CiticApiMessageConverter {

    /**
     * 构建余额查询请求报文对象
     *
     * @param request
     * @return
     */
    public DlsbalqrRequest fromAccountBalanceRequest(AccountBalanceRequest request) {

        Map<String, Object> channelConfig = request.getPaymentChannelConfig();
        String userName = String.valueOf(channelConfig.get("user_name"));
        String mainAccNo = String.valueOf(channelConfig.get("main_acc_no"));

        DlsbalqrRequest dlsbalqrRequest = DlsbalqrRequest.builder()
                .citicAction(CiticAction.DLSBALQR)
                .userName(userName)
                .mainAccNo(mainAccNo)
                .build();
        return dlsbalqrRequest;
    }

    /**
     * 构建余额查询应答报文对象
     *
     * @param dlsbalqrResponse
     * @return
     */
    public AccountBalanceResponse toAccountBalanceResponse(DlsbalqrResponse dlsbalqrResponse) {

        DlsbalqrResponseUserData userDataResponse = dlsbalqrResponse.getUserDataList().get(0);
        String kyAmt = "";
//                userDataResponse.getBalance();

        BigDecimal accountBalance = new BigDecimal(kyAmt);

        AccountBalanceResponse paymentAccountResponse = AccountBalanceResponse.builder()
                .accountBalance(accountBalance)
                .build();

        return paymentAccountResponse;
    }

}
