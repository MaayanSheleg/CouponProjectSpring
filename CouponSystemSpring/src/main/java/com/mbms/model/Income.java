package com.mbms.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String name;
	
	@Basic(optional = false )
	@Column(nullable = false)
	private String date;
	
	@Basic(optional = false )
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private IncomeType description;
	
	@Basic (optional = false)
	@Column (nullable = false)
	private double amount;
	

}
