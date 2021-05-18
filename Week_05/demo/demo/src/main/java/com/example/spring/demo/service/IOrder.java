package com.example.spring.demo.service;

/**
 * @author wei.huang
 * @version Id: IOrder.java, v 0.1 2021年05月18日  20:00 wei.huang Exp $
 */
public interface IOrder {

    /**
     * 提交订单信息并返回订单编号
     *
     * @param orderInfo
     * @return
     */
    String submitOrderInfo(String orderInfo);
}
