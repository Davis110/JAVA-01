package com.algorithm.data.order.service.impl;

import com.algorithm.data.order.bean.Order;
import com.algorithm.data.order.bean.OrderExample;
import com.algorithm.data.order.dao.OrderMapper;
import com.algorithm.data.order.service.OrderInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    OrderMapper orderMapper;

    @Override
    //@ReadOnly(name = "slave")
    public Page<Order> getInformationList(int pageIndex, int pageSize) {
        Page<Order> result = PageHelper.startPage(pageIndex, pageSize, true);
        OrderExample example = new OrderExample();
        orderMapper.selectByExample(example);
        return result;
    }

    @Override
    //@ReadOnly(name = "master")
    public Order selectByPrimaryKey(long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(Order orderInfo) {
        return orderMapper.updateByPrimaryKeySelective(orderInfo);
    }

    @Override
    public int insertInfo(Order orderInfo) {
        return orderMapper.insertSelective(orderInfo);
    }
}
