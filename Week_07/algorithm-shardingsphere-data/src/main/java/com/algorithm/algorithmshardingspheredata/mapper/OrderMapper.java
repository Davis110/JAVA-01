package com.algorithm.algorithmshardingspheredata.mapper;

import com.algorithm.algorithmshardingspheredata.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wei.huang
 * @version Id: OrderMapper.java, v 0.1 2021年04月27日  15:17 wei.huang Exp $
 */
@Mapper
public interface OrderMapper {

    int insert(Order order);

    Order selectByPrimaryKey(Long orderId);

    List<Order> selectByUserId(Long userId);
}
