package com.algorithm.data.source;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置数据源信息
 *
 * @author wei.huang
 * @version Id: DynamicDataSourceConfig.java, v 0.1 2021年04月20日  14:53 wei.huang Exp $
 */
@Configuration
@Component
public class DynamicDataSourceConfig {

    @Value("${datasource.slaves}")
    public String slaves;

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave1")
    public DataSource slaveOneDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave2")
    public DataSource slaveTwoDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveOneDataSource, DataSource slaveTwoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave1", slaveOneDataSource);
        targetDataSources.put("slave2", slaveOneDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources, "master", slaves);
    }
}
