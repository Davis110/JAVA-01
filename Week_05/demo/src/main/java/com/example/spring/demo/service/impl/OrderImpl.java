package com.example.spring.demo.service.impl;

import com.example.spring.demo.service.IOrder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author wei.huang
 * @version Id: OrderImpl.java, v 0.1 2021年05月18日  20:01 wei.huang Exp $
 */
@Service(value = "order0")
public class OrderImpl implements IOrder {
    @Override
    public String submitOrderInfo(String orderInfo) {
        System.out.println("提交的订单信息为：" + orderInfo);
        return UUID.randomUUID().toString();
    }
}
