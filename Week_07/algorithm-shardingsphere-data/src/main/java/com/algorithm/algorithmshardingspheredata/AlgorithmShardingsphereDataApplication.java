package com.algorithm.algorithmshardingspheredata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "com.algorithm.algorithmshardingspheredata.mapper" })
public class AlgorithmShardingsphereDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgorithmShardingsphereDataApplication.class, args);
    }

}
