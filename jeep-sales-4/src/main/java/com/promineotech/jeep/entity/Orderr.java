package com.promineotech.jeep.entity;



import java.util.List;

import lombok.Data;

@Data
public class Orderr {
	private Customer customer;
	private Jeep model;
	private Color color;
	private Engine engine;
	private Tire tire;
	private List<Option> options;

}
