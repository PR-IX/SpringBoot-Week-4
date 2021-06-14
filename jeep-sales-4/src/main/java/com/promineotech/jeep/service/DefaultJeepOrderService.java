package com.promineotech.jeep.service;

import java.awt.Color;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Tire;


@Service
public class DefaultJeepOrderService implements JeepOrderService {

	@Autowired
	private JeepOrderDao jeepOrderDao;
	
	@Transactional
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		Customer customer = jeepOrderDao.fetchCustomer(orderRequest.getCustomer()).orElseThrow() -> 
		new NoSuchElementException()"Customer with ID=" + orderRequest,getCustomer() + "was not found");
		
		Jeep jeep = jeepOrderDao
				.fetchModel(orderRequest.getModel(), orderRequest.getTrim(),
						orderRequest.getDoors()).orElseThrow() -> 
				new NoSuchElementException()"Model with ID=" + orderRequest,getCustomer() + "was not found");;
		
		Color color = jeepOrderDao.fetchColor(orderRequest.getColors()).orElseThrow() -> 
		new NoSuchElementException("Color with ID=" + orderRequest,getCustomer() + "was not found");;
		
		Engine engine = jeepOrderDao.fetchEngine(orderRequest.getEngine()).orElseThrow() -> 
		new NoSuchElementException("Engine with ID=" + orderRequest,getCustomer() + "was not found");;
		
		Tire tire = jeepOrderDao.fetchTire(orderRequest.getTire()).orElseThrow() -> 
		new NoSuchElementException("Tire with ID=" + orderRequest,getCustomer() + "was not found");;
		
		
		
			return null;
	}

}
