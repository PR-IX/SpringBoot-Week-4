package com.promineotech.jeep.entity;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jeep implements Comparable<Jeep>{
	
	private Long modelPK ;
	private JeepModel modelid;
	private String trimlevel;
	private int numDoors;
	private int wheelSize;
	private BigDecimal basePrice;
	
	@JsonIgnore
  public Long getModelPK() {
	  return modelPK;
  }
	
	@Override
	public int compareTo(Jeep that) {
		return Comparator
				.comparing(Jeep::getModelid)
				.thenComparing(Jeep::getTrimlevel)
				.thenComparing(Jeep::getNumDoors)
				.compare(this, that);
		
	}
}
