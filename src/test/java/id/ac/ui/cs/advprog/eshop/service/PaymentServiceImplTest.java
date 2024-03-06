package id.ac.ui.cs.advprog.eshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

class PaymentServiceImplTest {
  @InjectMocks
  PaymentServiceImpl paymentService;

  @Mock
  PaymentRepository paymentRepository;

  List<Payment> payments;
  Order order;
  Map<String,String> mapVC;
  Map<String,String> mapCOD;

  @BeforeEach
  void setUp() {
    payments = new ArrayList<>();
    
    mapVC = new HashMap<>();
    mapVC.put("voucherCode", "ESHOP192745ADA12");
    
    mapCOD = new HashMap<>();
    mapCOD.put("address", "Suatu Tempat");
    mapCOD.put("deliveryFee", "5 triliun belum termasuk pajak");
    
    List<Product> products = new ArrayList<>();
    Product product1 = new Product();
    product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product1.setProductName("Sampo Cap Bambang");
    product1.setProductQuantity(2);
    products.add(product1);
    order = new Order("13652556-012a04c07-b546-54eb1396d79b",
      products, 1708560000L, "Safira Sudrajat");

    Payment payment1 = new Payment("1301vbv9-ano81v371-la83qbo8", 
      PaymentMethods.VC.getValue(), mapVC);
    Payment payment2 = new Payment("1uotg8g1-afg0q8gq3-awiq3uo8", 
      PaymentMethods.VC.getValue(), mapCOD);
    payments.add(payment2);
    payments.add(payment1);
  }
  
  @Test
  void testAddPayment() {
    Payment payment = payments.get(1);
    doReturn(payment).when(paymentRepository).save(payment);

    Payment result = paymentService.addPayment(order, payment);
    verify(paymentRepository, times(1)).save(payment);
    assertEquals(payment.getId(), result.getId());
  }

  @Test
  void testAddPaymentIfVCIfInvalid() {
    Payment payment = payments.get(0);
    Map<String,String> mapVCInvalid = new HashMap<>();
    mapVCInvalid.put("voucherCode", "BAJAK192745ADA12");
    payment.setPaymentData(mapVCInvalid);
    assertThrows(IllegalArgumentException.class, 
      () -> paymentService.addPayment(order, payment));

    verify(paymentRepository, times(0)).save(any(Payment.class));
  }

  @Test
  void testAddPaymentIfCODIfInvalid() {
    Payment payment = payments.get(1);
    Map<String,String> mapCODInvalid = new HashMap<>();
    mapCODInvalid.put("address", null);
    mapCODInvalid.put("deliveryFee", null);
    payment.setPaymentData(mapCODInvalid);
    assertThrows(IllegalArgumentException.class, 
      () -> paymentService.addPayment(order, payment));

    verify(paymentRepository, times(0)).save(any(Payment.class));
  }

  @Test
  void testSetStatusIfValid() {
    Payment payment = payments.get(0);
    Payment result = new Payment(payment.getId(), payment.getMethod(), 
      PaymentStatus.SUCCESS.getValue(),mapVC);
    doReturn(result).when(paymentRepository).save(result);
    paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
    assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
  }

  @Test
  void testGetPaymentIfValid() {
    Payment payment = payments.get(1);
    doReturn(payment).when(paymentRepository).findById(payment.getId());
    Payment result = paymentService.findById(payment.getId());
    assertEquals(payment.getId(), result.getId());
  }

  @Test
  void testGetPaymentIfInvalid() {
    doReturn(null).when(orderRepository).findById("zczc");
    assertNull(orderService.findById("zczc"));
  }

  @Test
  void testGetAllPayments() {
    doReturn(payments).when(paymentRepository).getAll();
    List<Payment> result = paymentService.testGetAllPayments();
    assertEquals(2, result.size());
  }
}
