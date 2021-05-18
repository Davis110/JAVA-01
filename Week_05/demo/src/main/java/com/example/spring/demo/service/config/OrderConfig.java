package com.example.spring.demo.service.config;

import com.example.spring.demo.service.IOrder;
import com.example.spring.demo.service.impl.OrderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wei.huang
 * @version Id: OrderConfig.java, v 0.1 2021年05月18日  20:09 wei.huang Exp $
 */
@Configuration
public class OrderConfig {
    @Bean(name = "order1")
    public IOrder getOrderBean() {
        return new OrderImpl();
    }
}
