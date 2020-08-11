package com.base.frame.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "base.config")
@PropertySource({"classpath:application.yml"})
@Component
@Data
public class BaseConfig {

    /**
     * AES加密Key
     * */
    private String aesKey;
    /**
     * 是否检查token有效期
     * */
    private boolean checkTokenTime;
    /**
     * token有效期, (单位: 天)
     * */
    private Integer tokenTimeLimit;

}
