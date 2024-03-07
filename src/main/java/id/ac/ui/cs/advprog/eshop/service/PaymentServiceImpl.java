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
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentServiceImpl implements PaymentService {
  @Autowired
  private PaymentRepository paymentRepository;
  private HashMap<String, Order> paymentOrders;

  @Override
  public Payment addPayment(Order order, String method, 
    Map<String, String> paymentData){
    if (method.equals(PaymentMethods.VC.getValue())) {
      String code = paymentData.get("voucherCode");
      validateVoucher(code);
      return save(order, method, paymentData);

    } else if (method.equals(PaymentMethods.COD.getValue())) {
      String adress = paymentData.get("adress");
      String deliveryFee = paymentData.get("deliveryFee");
      validateCOD(adress, deliveryFee);
      return save(order, method, paymentData);

    } else {
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

  public void validateVoucher(String voucherCode) {
    int count = 0;
    String numbers = "1234567890";
    if (voucherCode.length() != 16 ||
        !voucherCode.substring(0, 5).
        equals("ESHOP")
      ) {
      throw new IllegalArgumentException();
    } else {
      for (char letter : voucherCode.toCharArray()) {
        if (numbers.contains(String.valueOf(letter))) {
          count += 1;
        }
      }
      if (count != 8) {
        throw new IllegalArgumentException();
      }
    }
  }

  public void validateCOD(String adress, String deliveryFee) {
    if (adress == null || deliveryFee == null) {
      throw new IllegalArgumentException();
    } else if (adress.isEmpty() || deliveryFee.isEmpty()) {
      throw new IllegalArgumentException();  
    }
  }

  public Payment save(Order order, String method, Map<String,String> paymentData) {
    String id = UUID.randomUUID().toString();
    paymentOrders.put(id, order);   
    return paymentRepository.save(new Payment(
      id, method, paymentData));
  }
}
