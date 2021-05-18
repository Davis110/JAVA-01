package com.algorithm.data.order.service;

import com.algorithm.data.order.bean.Order;
import com.github.pagehelper.Page;

public interface OrderInfoService {

    Page<Order> getInformationList(int pageIndex, int pageSize);

    Order selectByPrimaryKey(long order);

    int updateByPrimaryKeySelective(Order orderInfo);

    int insertInfo(Order record);

}
