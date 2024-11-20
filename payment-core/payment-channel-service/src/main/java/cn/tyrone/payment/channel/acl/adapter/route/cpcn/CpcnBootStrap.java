package cn.tyrone.payment.channel.acl.adapter.route.cpcn;

import com.trz.netwk.api.system.InitSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 中金支付引导程序，用于加载配置文件
 */
@WebListener
@Slf4j
public class CpcnBootStrap implements ServletContextListener {

//    private static String

    @Autowired
    private Environment environment;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String configUrl = environment.getProperty("payment.channel.cpcn.configUrl");
        log.debug("中金配置文件地址：" + configUrl);
        try {

            InitSystem.initialize(configUrl);

            log.debug("中金支付配置文件初始化已成功");

        } catch (Exception e) {
            log.error("中金支付配置文件初始化失败：", e);
        }

    }
}



