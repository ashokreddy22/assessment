package com.product.details;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int productId;
	
private String productname;
	
private Double productcost;

private String productstock;
public Product( String productname, Double cost, String productstock) {
	super();

	this.productname = productname;
	this.productcost = cost;
	this.productstock = productstock;
}

public Product() {
}

public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public Double getProductcost() {
	return productcost;
}
public void setProductcost(Double productcost) {
	this.productcost = productcost;
}
public String getProductstock() {
	return productstock;
}
public void setProductstock(String productstock) {
	this.productstock = productstock;
}
@Override
public String toString() {
	return "Product [productId=" + productId + ", productname=" + productname + ", productcost=" + productcost
			+ ", productstock=" + productstock + "]";
}

}
