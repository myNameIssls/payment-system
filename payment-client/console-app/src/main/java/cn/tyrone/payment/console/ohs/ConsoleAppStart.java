package cn.tyrone.payment.console.ohs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ConsoleAppStart implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleAppStart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("-------------- 控制台启动成功 ---------------");
    }
}
