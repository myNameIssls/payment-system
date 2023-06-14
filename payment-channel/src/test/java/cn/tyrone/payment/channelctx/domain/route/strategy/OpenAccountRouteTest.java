package cn.tyrone.payment.channelctx.domain.route.strategy;

import cn.tyrone.payment.channelctx.domain.route.strategy.context.AccountBalanceRouteContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ComponentScan(basePackages = {
        "cn.tyrone.payment.channelctx.domain.route"
})
class OpenAccountRouteTest {

    @Autowired
    AccountBalanceRouteContext accountBalanceRouteContext;

    @Test
    void openAccount() {



    }
}