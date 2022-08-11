package com.product.details;

import java.util.List;

public class Product {
private int productId;
private String productname;
private Double productcost;
private String productstock;
public Product(int productId, String productname, Double productcost, String productstock) {
	super();
	this.productId = productId;
	this.productname = productname;
	this.productcost = productcost;
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
