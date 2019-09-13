package com.mbms.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="COMPANY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Basic(optional = false)
	@Column (nullable = false, name = "companyName")
	private String companyName;
	
	@Basic(optional = false)
	@Column (nullable = false, name = "password")
	private String password;
	
	@Basic(optional = false)
	@Column (nullable = false, name = "email")
	private String email;
	
}
