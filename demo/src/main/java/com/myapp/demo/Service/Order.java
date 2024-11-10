package com.myapp.demo.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.myapp.demo.Model.OrderRequest;
import com.myapp.demo.Repository.OrderRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.annotation.PostConstruct;

@Service
public class Order {

	@Autowired
	private OrderRepository orderRepository;
	
	@Value("${razorpay.key.id}")
	private String razorpayId;
	
	@Value("${razorpay.key.secret}")
	private String razorpaySecret;
	
	private RazorpayClient razorpayclient;
	
	@PostConstruct
	public void init() throws RazorpayException {
		this.razorpayclient = new RazorpayClient(razorpayId, razorpaySecret);
	}
	
	public OrderRequest createorder(OrderRequest order) throws RazorpayException {
		JSONObject json = new JSONObject();
		
		json.put("amount", order.getAmount());
		json.put("email", order.getEmail());
		json.put("name", order.getName());
		json.put("razorpayid", order.getRazorpayorderid());
		json.put("status", order.getStatus());
		
		com.razorpay.Order razorpayOrder = razorpayclient.Orders.create(json);
		
		order.setRazorpayorderid(razorpayOrder.get("razorpayorderid"));
		order.setStatus(razorpayOrder.get("status"));
		
		return orderRepository.save(order);
		
	}
}	
	