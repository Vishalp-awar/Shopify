package com.myapp.demo.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.myapp.demo.Model.OrderRequest;
import com.myapp.demo.Repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;
import com.myapp.demo.Model.AddressDetails;
@RestController
@RequestMapping("/api/payment")

@CrossOrigin(origins = "http://localhost:3000") 
public class PaymentController {

    @Autowired
    OrderRepository orderRepository;
    
//	@PostMapping("/createOrder")
//	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
//			try
//			{
//				System.out.println("Received order request: " + orderRequest);
//
//				
//				orderRepository.save(orderRequest);
//			
//			}catch (HttpClientErrorException e) {
//			    // Log the response body and headers for debugging
//			    System.out.println("Error details: " + e.getResponseBodyAsString());
//			    return ResponseEntity.status(e.getStatusCode()).body("Error creating order: " + e.getResponseBodyAsString());
//			}
//	    String url = "https://api.razorpay.com/v1/orders";
//	    RestTemplate restTemplate = new RestTemplate();
//	    
//	    
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    headers.setBasicAuth("rzp_test_yM8G2mLzxl7OVh", "9ZBuqNqY16PQa7CkTUHtcXAF");
//
//	    Map<String, Object> orderDetails = new HashMap<>();
//	    orderDetails.put("amount", orderRequest.getAmount()); // Ensure this is in paise
//	    orderDetails.put("currency", "INR");
//	    orderDetails.put("receipt", "order_rcptid_11");
//	    orderDetails.put("payment_capture", 1);
//
//	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(orderDetails, headers);
//
//	    try {
//	        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
//	        return ResponseEntity.ok(response.getBody());
//	    } catch (HttpClientErrorException e) {
//	        return ResponseEntity.status(e.getStatusCode()).body("Error creating order: " + e.getResponseBodyAsString());
//	    } catch (Exception e) {
//	        return ResponseEntity.status(500).body("Error creating order: " + e.getMessage());
//	    }
//	}
    
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            System.out.println("Received order request: " + orderRequest);

            // Create AddressDetails object and set values
            AddressDetails addressDetails = new AddressDetails();
            addressDetails.setAddress1(orderRequest.getAddressDetails().getAddress1());
            addressDetails.setAddress2(orderRequest.getAddressDetails().getAddress2());
            addressDetails.setCity(orderRequest.getAddressDetails().getCity());
            addressDetails.setState(orderRequest.getAddressDetails().getState());
            addressDetails.setZip(orderRequest.getAddressDetails().getZip());
            addressDetails.setCountry(orderRequest.getAddressDetails().getCountry());

            // Set AddressDetails in OrderRequest
            orderRequest.setAddressDetails(addressDetails);

            // Save the order request
            orderRepository.save(orderRequest);
            
        } catch (HttpClientErrorException e) {
            System.out.println("Error details: " + e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).body("Error creating order: " + e.getResponseBodyAsString());
        }

        // Razorpay order creation logic
        String url = "https://api.razorpay.com/v1/orders";
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("rzp_test_yM8G2mLzxl7OVh", "9ZBuqNqY16PQa7CkTUHtcXAF");

        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("amount", orderRequest.getAmount()); // Ensure this is in paise
        orderDetails.put("currency", "INR");
        orderDetails.put("receipt", "order_rcptid_11");
        orderDetails.put("payment_capture", 1);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(orderDetails, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Error creating order: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating order: " + e.getMessage());
        }
    }

}
