package com.algorithm.algorithmshardingspheredata.mapper;

import com.algorithm.algorithmshardingspheredata.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author wei.huang
 * @version Id: OrderMapper.java, v 0.1 2021年04月26日  14:15 wei.huang Exp $
 */
@Mapper
@Repository
public interface OrderMapper_def {
    @Insert("insert into t_order (order_id, order_no, user_id, order_amount, order_status,remark) " +
            "values " +
            "(#{order.orderId},#{order.orderNo},#{order.userId},#{order.orderAmount},#{order.orderStatus},#{order.remark})")
    void addOrder(@Param("order") Order order);

    @Select("select * from t_order where order_id = #{orderId}")
    @Results({
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_amount", property = "orderAmount"),
            @Result(column = "order_status", property = "orderStatus")
    })
    Order getOrder(@Param("orderId") Long orderId);
}
