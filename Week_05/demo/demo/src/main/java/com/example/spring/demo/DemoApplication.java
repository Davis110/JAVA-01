package com.example.spring.demo;

import com.example.spring.demo.service.IOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context1 = SpringApplication.run(DemoApplication.class, args);
        IOrder order0 = (IOrder) context1.getBean("order0");
        order0.submitOrderInfo("这是通过注解扫描装载的bean!");
        IOrder order1 = (IOrder) context1.getBean("order1");
        order1.submitOrderInfo("这是通过java类装载的bean!");
        ApplicationContext context2 = new ClassPathXmlApplicationContext("spring-xml/spring-order.xml");
        IOrder order2 = (IOrder) context2.getBean("order2");
        order2.submitOrderInfo("这是通过xml装载的bean!");
    }

}
