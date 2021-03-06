package com.promineotech.jeep.entity;

import java.math.BigDecimal;

import org.graalvm.compiler.options.OptionType;

import lombok.Data;

@Data
public class Option {
	private Long optionPK;
	private String optionId;
	private OptionType category;
	private String manufacturer;
	private String name;
	private BigDecimal price;
}
