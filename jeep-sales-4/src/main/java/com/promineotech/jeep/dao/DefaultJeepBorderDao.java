package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.service.JeepOrderDao;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultJeepBorderDao implements JeepOrderDao {
   @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

	
	
	public Customer fetchCustomer(String customerId){


	public Order creatOrder
	(OrderRequest orderRequest) {
		String sql = ""
				+"SELECT * "
		        +"FROM customers"
		        +"WHERE customer_id = :customer_id";
		
		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);
		return Optional.ofNullable(null).query(sql, params, new ResultSetExtractor<Customer>() {})
	}
	
	class CustomerResultSetExtractor implements ResultSetExtractor<Customer>{
		
	}
	
	}

			
			
				
					
				
				
				
				
			
	
				
			
			
			
		
	
		
	

		

	




