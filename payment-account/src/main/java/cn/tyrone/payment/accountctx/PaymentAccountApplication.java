package cn.tyrone.payment.accountctx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PaymentAccountApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PaymentAccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("-------------- 支付账户服务启动成功 ---------------");
    }
}
