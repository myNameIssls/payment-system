package cn.tyrone.payment.channelctx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class PaymentChannelApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PaymentChannelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("-------------- 支付渠道网关服务启动成功 ---------------");
    }
}
