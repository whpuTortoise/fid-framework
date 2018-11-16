package com.tortoise.quake.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author wilson
 * @email wangzhi@yunify.com
 * @date 2018年11月15日 下午4:34:06
 */
@Configuration
public class DruidConfig {
	/**
     * 加入到Spring容器中，并扫描spring.datasource前缀的配置
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }
}

