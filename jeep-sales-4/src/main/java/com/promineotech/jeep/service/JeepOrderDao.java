package com.promineotech.jeep.service;

import java.awt.Color;
import java.util.Optional;

import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Tire;

public interface JeepOrderDao {

Optional<Customer> fetchCustomer(String customerId);

Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);

Optional<Color> fetchColor(String engineId);

Optional<Engine> fetchEngine(String engineId);

Optional<Tire> fetchTire(String tireId);
}
