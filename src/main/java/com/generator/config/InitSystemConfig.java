package com.generator.config;

import com.yuntongxun.base.wechat.util.CommonUtil;
import com.yuntongxun.base.wechat.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description: 初始化系统
 * @Author: lulongji
 * @Date: Created in 15:53 2018/11/17
 */

@Component
@Order(-99)
public class InitSystemConfig implements CommandLineRunner, EnvironmentAware {


    private static final Logger logger = LoggerFactory.getLogger(InitSystemConfig.class);


    @Override
    public void run(String... strings) throws Exception {
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}
