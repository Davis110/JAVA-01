package com.algorithm.data;

import com.algorithm.data.order.bean.Order;
import com.algorithm.data.order.service.OrderInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class AlgorithmDataApplicationTests {

    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSave() {
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setOrderNo("order_00" + i);
            order.setOrderAmount(new BigDecimal(i));
            order.setOrderStatus(0);
            order.setUserId((long) i);
            orderInfoService.insertInfo(order);
        }
    }

    //@Test
    public void testGetById() {
        int id = 1002323214;
        Order order = orderInfoService.selectByPrimaryKey(id);
        System.out.println(order.toString());
    }

}
