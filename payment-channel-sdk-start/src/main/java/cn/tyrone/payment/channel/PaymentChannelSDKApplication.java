package cn.tyrone.payment.channel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Slf4j
@SpringBootApplication
public class PaymentChannelSDKApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentChannelSDKApplication.class, args);
        System.out.println("0000");
    }

//    @Override
//    public void run(String... args) throws Exception {
//        log.debug("-------------- 支付渠道网关服务启动成功 ---------------");
//    }
}
