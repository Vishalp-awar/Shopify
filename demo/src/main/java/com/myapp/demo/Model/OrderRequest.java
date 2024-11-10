package com.myapp.demo.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customorder")
public class OrderRequest {
	
	@Id
    private ObjectId  orderId;
    private int amount;
    private String razorpayorderid;
    private String name;
    private String email;
    private String status;
    
    private AddressDetails addressDetails;

    // Default constructor
    public OrderRequest() {}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRazorpayorderid() {
		return razorpayorderid;
	}
	public void setRazorpayorderid(String razorpayorderid) {
		this.razorpayorderid = razorpayorderid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ObjectId getOrderId() {
		return orderId;
	}
	public void setOrderId(ObjectId orderId) {
		this.orderId = orderId;
	}
	public AddressDetails getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}
    
    
}
