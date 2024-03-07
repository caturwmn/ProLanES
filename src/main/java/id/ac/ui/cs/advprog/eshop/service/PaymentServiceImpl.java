package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentServiceImpl implements PaymentService {
  @Autowired
  private PaymentRepository paymentRepository;
  private HashMap<String, Order> paymentOrders;

  @Override
  public Payment addPayment(Order order, String method, 
    Map<String, String> paymentData){
      
    String id = UUID.randomUUID().toString();
    String number = "0123456789";
    switch(method) {
      case "Voucher Code":
        String code = paymentData.get("voucherCode");
        int count = 0;
        if (code.length() != 16) {
          throw new IllegalArgumentException();
        } else {
          if (code.substring(0, 5).equals("ESHOP")) {
            for (char letter : code.toCharArray()) {
              if (number.contains(String.valueOf(letter))) {
                count += 1;
              }
            }
            if (count != 8) {
              throw new IllegalArgumentException();
            }
          } else {
            throw new IllegalArgumentException();
          }
        }

        paymentOrders.put(id, order);   
        return paymentRepository.save(new Payment(
          id, method, paymentData));
      
      case "Cash On Destination":
        String adress = paymentData.get("adress");
        String deliveryFee = paymentData.get("deliveryFee");
        if (adress == null || deliveryFee == null) {
          throw new IllegalArgumentException();
        } else if (adress.isEmpty() || deliveryFee.isEmpty()) {
          throw new IllegalArgumentException();  
        } else {
          paymentOrders.put(id, order);   
          return paymentRepository.save(new Payment(
            id, method, paymentData));
        }
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public Payment setStatus(Payment payment, String status) {
    Order order = paymentOrders.get(payment.getId());
    switch (status) {
      case "SUCCESS":
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        order.setStatus(OrderStatus.SUCCESS.getValue());
        return payment;
      case "REJECTED":
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        order.setStatus(OrderStatus.FAILED.getValue());
        return payment;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public Payment getPayment(String paymentId) {
    return paymentRepository.findById(paymentId);
  }

  @Override
  public List<Payment> getAllPayments() {
    return paymentRepository.getAll();
  }
}
