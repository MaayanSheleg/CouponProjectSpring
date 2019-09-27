package com.mbms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon{
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Basic (optional = false)
	@Column (nullable = false, name = "title")
	private String title;
	
	@Basic(optional = false)
	@Column(nullable = false, name="startDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;


	@Basic(optional = false)
	@Column(nullable = false, name="endDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date endDate;

	@Basic(optional = false)
	@Column(nullable = false, name="amount")
	private int amount;

	@Basic(optional = false)
	@Column(nullable = false, name="message")
	private String message;

	@Basic(optional = false)
	@Column(nullable = false, name="price")
	private double price;

	@Basic(optional = false)
	@Column(nullable = false, name="image")
	private String image;

	@Basic(optional = false)
	@Column(nullable = false, name="type")
	@Enumerated(EnumType.STRING)
	private CouponType type;
	
//	@ManyToOne
//	@Valid
//	private Company company;
//	
//	@ManyToMany(mappedBy = "coupons")
//	@Valid
//	private List<Customer> customers;

}
