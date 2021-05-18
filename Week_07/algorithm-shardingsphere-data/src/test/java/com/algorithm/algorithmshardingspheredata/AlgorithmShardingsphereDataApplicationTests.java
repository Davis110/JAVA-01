package com.algorithm.algorithmshardingspheredata;

import com.algorithm.algorithmshardingspheredata.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlgorithmShardingsphereDataApplicationTests {

    @Autowired OrderService orderService;

    @Test
    void insetOrderInfo() {
        for(int i=1;i<5;i++){
            orderService.addOrder(i);
        }
    }

}
