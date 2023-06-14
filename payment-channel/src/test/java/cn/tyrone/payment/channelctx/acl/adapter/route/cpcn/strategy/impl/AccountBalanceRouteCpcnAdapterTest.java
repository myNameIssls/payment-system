package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.strategy.impl;

import cn.tyrone.payment.channelctx.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.route.strategy.AccountBalanceRoute;
import cn.tyrone.payment.channelctx.domain.route.strategy.context.AccountBalanceRouteContext;
import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
import cn.tyrone.payment.channelctx.pl.AccountBalanceResponse;
import com.trz.netwk.api.system.InitSystem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ComponentScan(basePackages = {
        "cn.tyrone.payment.channelctx.domain.route"
})
class AccountBalanceRouteCpcnAdapterTest {

    @Autowired
    AccountBalanceRouteContext accountBalanceRouteContext;

    @BeforeAll
    static void beforeAll() throws Exception {

        InitSystem.initialize("/Users/shanglishuai/temp/conf/zjconf");

    }

    @Test
    void accountBalance() {

        AccountBalanceRequest accountBalanceRequest = AccountBalanceRequest.builder()
                .paymentChannelUniqueCode(PaymentChannelUniqueCode.CPCN_ONE.uniqueValue)
                .subAccount("2308711003921814").subAccountName("中金二期测试供应商六")
                .build();
        AccountBalanceRoute accountBalanceRoute = accountBalanceRouteContext.getOpenAccountRoute(accountBalanceRequest);
        AccountBalanceResponse accountBalanceResponse = accountBalanceRoute.accountBalance(accountBalanceRequest);
        log.debug("账户余额查询结果：{}", accountBalanceResponse.toString());
    }
}