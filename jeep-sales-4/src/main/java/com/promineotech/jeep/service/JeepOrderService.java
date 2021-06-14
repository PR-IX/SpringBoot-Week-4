package com.promineotech.jeep.service;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.promineotech.jeep.entity.OrderRequest;

public interface JeepOrderService {

	Order createOrder(OrderRequest orderRequest);

}
