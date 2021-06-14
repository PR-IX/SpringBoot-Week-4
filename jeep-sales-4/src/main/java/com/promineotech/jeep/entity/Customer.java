package com.promineotech.jeep.entity;

import lombok.Data;

@Data
public class Customer {
	
	private Long customerPK;
	private String customerId;
	private String firstname;
	private String lastname;
	private String phone;

}
