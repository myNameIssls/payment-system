package cn.tyrone.payment.channelctx.domain.route.strategy.context;

import cn.tyrone.payment.channelctx.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.route.strategy.OpenAccountRoute;
import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
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
class OpenAccountRouteContextTest {

    @Autowired
    private OpenAccountRouteContext openAccountRouteContext;

    @Test
    void getOpenAccountRoute() {

        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .paymentChannelUniqueCode(PaymentChannelUniqueCode.CPCN_ONE.uniqueValue)
                .build();

        OpenAccountRoute openAccountRoute = openAccountRouteContext.getOpenAccountRoute(openAccountRequest);

        log.debug(openAccountRoute.getClass().getName());

    }
}