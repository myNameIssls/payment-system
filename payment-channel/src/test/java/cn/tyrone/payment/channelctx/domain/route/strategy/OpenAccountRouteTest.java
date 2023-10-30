package cn.tyrone.payment.channelctx.domain.route.strategy;

import cn.tyrone.payment.channelctx.domain.route.strategy.context.AccountBalanceRouteContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

//@Slf4j
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.NONE
//)
//@ComponentScan(basePackages = {
//        "cn.tyrone.payment.channelctx.domain.route"
//})
class OpenAccountRouteTest {

    @Autowired
    AccountBalanceRouteContext accountBalanceRouteContext;

    @Test
    void openAccount() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("a", "a");
        System.out.printf(map.toString());

    }
}