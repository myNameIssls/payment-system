package cn.tyrone.payment.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"cn.tyrone.payment.channel"})
public class PaymentChannelSDKApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(PaymentChannelSDKApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("-------------- 支付渠道网关服务启动成功 ---------------");
    }
}
