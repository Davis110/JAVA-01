package com.algorithm.data.order.controller;

import com.algorithm.data.order.bean.Order;
import com.algorithm.data.order.service.OrderInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wei.huang
 * @version Id: OrderInfoController.java, v 0.1 2021年04月20日  15:18 wei.huang Exp $
 */
@RestController
@RequestMapping
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("/GetOrderById")
    public Order GetOrderById() {
        return orderInfoService.selectByPrimaryKey(11);
    }

    @GetMapping("/ListOrder")
    public List<Order> ListOrder() {
        Page<Order> orderList = orderInfoService.getInformationList(1, 10);
        return orderList.getResult();
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void saveOrder() {
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setOrderNo("order_00" + i);
            order.setOrderAmount(new BigDecimal(i));
            order.setOrderStatus(0);
            order.setUserId((long) i);
            orderInfoService.insertInfo(order);
        }
        return;
    }
}
