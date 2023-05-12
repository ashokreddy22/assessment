package com.in.hotel.model;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Bill.getAllBills",query="select b from Bill b order by b.id desc")
@NamedQuery(name="Bill.getBillByUserName",query="select b from Bill b where b.createBy=:userName order by b.id desc")
@Entity
@Table(name="bill")
@DynamicInsert
@DynamicUpdate
public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private Integer id;

@Column(name="uuid")
private String uuid;

@Column(name="name")
private String name;

@Column(name="email")
private String email;


@Column(name="contactnumber")
private String contactNumber;


@Column(name="paymentmethod")
private String paymentMethod;


@Column(name="total")
private Integer total;



@Column(name="productdetails",columnDefinition="json")
private String productDetails;


@Column(name="createby")
private String createBy;


public Bill() {
	super();
	// TODO Auto-generated constructor stub
}




public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public String getUuid() {
	return uuid;
}


public void setUuid(String uuid) {
	this.uuid = uuid;
}


public String getName() {
	return name;
}


public void setName(String object) {
	this.name = object;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getContactNumber() {
	return contactNumber;
}


public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}


public String getPaymentMethod() {
	return paymentMethod;
}


public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
}





/**
 * @return the totalAmount
 */
public Integer getTotalAmount() {
	return total;
}




/**
 * @param totalAmount the totalAmount to set
 */
public void setTotalAmount(Integer totalAmount) {
	this.total = totalAmount;
}




public String getProductDetails() {
	return productDetails;
}


public void setProductDetails(String productDetails) {
	this.productDetails = productDetails;
}


public String getCreateBy() {
	return createBy;
}


public void setCreateBy(String createBy) {
	this.createBy = createBy;
}





}
