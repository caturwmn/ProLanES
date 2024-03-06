package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentRepositoryTest {
  PaymentRepository paymentRepository;
  List<Payment> payments;
  Map<String,String> map;

  boolean compare(Payment payment1, Payment payment2) {
    if (payment1.getId().equals(payment2.getId()) && 
        payment1.getMethod().equals(payment2.getMethod()) &&
        payment1.getStatus().equals(payment2.getStatus())) {
      return true;
    } else {
      return false;
    }
  }

  @BeforeEach
  void setUp() {
    paymentRepository = new PaymentRepository();
    payments = new ArrayList<>();
    map = new HashMap<>();
    map.put("voucherCode", "ESHOP192745ADA12");
    Payment payment1 = new Payment("1301vbv9-ano81v371-la83qbo8", 
      PaymentMethods.VC.getValue(), map);
    Payment payment2 = new Payment("1uotg8g1-afg0q8gq3-awiq3uo8", 
      PaymentMethods.VC.getValue(), map);
    payments.add(payment2);
    payments.add(payment1);
  }

  @Test
  void testSaveCreateAndFindByID() {
    Payment payment = payments.get(1);
    Payment result = paymentRepository.save(payment);

    Payment findResult = paymentRepository.findById(payments.get(1).getId());
    assertEquals(payment.getId(), result.getId());
    assertTrue(compare(payment, findResult));
    assertEquals(payment.getPaymentData(), findResult.getPaymentData());
  }

  @Test
  void testSaveUpdate() {
    Payment payment = payments.get(1);
    paymentRepository.save(payment);
    Payment newPayment = new Payment(payment.getId(), payment.getMethod(), 
      PaymentStatus.SUCCESS.getValue(), payment.getPaymentData());
    Payment result = paymentRepository.save(newPayment);

    Payment findResult = paymentRepository.findById(payments.get(1).getId());
    assertEquals(payment.getId(), findResult.getId());
    assertEquals(payment.getId(), findResult.getId());
    assertEquals(payment.getMethod(), findResult.getMethod());
    assertTrue(compare(result, findResult));
    assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
  }

  @Test
  void testFindByIdIfIdNotFound() {
    for (Payment payment : payments) {
      paymentRepository.save(payment);
    }

    Payment findResult = paymentRepository.findById("whydoesthishappenstome");
    assertNull(findResult);
  }

  @Test
  void testGetAllPayments() {
    for (Payment payment : payments) {
      paymentRepository.save(payment);
    }

    List<Payment> paymentList = paymentRepository.getAll();
    assertEquals(2, paymentList.size());
    assertTrue(compare(payments.get(0), paymentList.get(0)));
    assertTrue(compare(payments.get(1), paymentList.get(1)));  
  }
}
