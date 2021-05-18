package com.algorithm.algorithmshardingspheredata.service;

import com.algorithm.algorithmshardingspheredata.mapper.OrderMapper;
import com.algorithm.algorithmshardingspheredata.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author wei.huang
 * @version Id: OrderService.java, v 0.1 2021年04月26日  14:16 wei.huang Exp $
 */
@Service
public class OrderService {

    @Resource
    OrderMapper orderMapper;

    public Long addOrder(long orderId) {
        //通过日志可以看出插入数据默认使用了主库ds0
        Order order = new Order();
        order.setUserId(orderId + 1);
        order.setOrderNo("order_001");
        order.setOrderStatus(1);
        order.setOrderAmount(new BigDecimal(1000));
        orderMapper.insert(order);
        System.out.println("addOrder ===> " + order.getOrderId());
        return order.getOrderId();
    }

    public Order getOrder() {
        //通过日志可以看出查询数据轮询了配置文件中的ds1和ds2
        Order order = orderMapper.selectByPrimaryKey(1L);
        System.out.println("getOrder ===> " + order);
        return order;
    }
}
