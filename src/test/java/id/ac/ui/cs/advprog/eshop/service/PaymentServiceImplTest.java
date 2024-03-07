package id.ac.ui.cs.advprog.eshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
  @InjectMocks
  PaymentServiceImpl paymentService;

  @Mock
  PaymentRepository paymentRepository;

  @Mock
  HashMap<String, Order> paymentOrders;

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
    lenient().doReturn(payment).when(paymentRepository).save(payment);
    lenient().doReturn(payment.getPaymentData()).when(paymentOrders).get(anyString());

    Payment result = paymentService.addPayment(order, 
      payment.getMethod(), payment.getPaymentData());
    verify(paymentRepository, times(1)).save(any(Payment.class));
  }

  @Test
  void testAddPaymentIfVCIfInvalid() {
    Map<String,String> mapVCInvalid = new HashMap<>();
    mapVCInvalid.put("voucherCode", "BAJAK192745ADA12");
    assertThrows(IllegalArgumentException.class, 
      () -> paymentService.addPayment(order, 
        PaymentMethods.VC.getValue(), mapVCInvalid
    ));

    verify(paymentRepository, times(0)).save(any(Payment.class));
  }

  @Test
  void testAddPaymentIfCODIfInvalid() {
    Map<String,String> mapCODInvalid = new HashMap<>();
    mapCODInvalid.put("address", null);
    mapCODInvalid.put("deliveryFee", null);
    assertThrows(IllegalArgumentException.class, 
      () -> paymentService.addPayment(order,
        PaymentMethods.COD.getValue(), mapCODInvalid
      ));

    verify(paymentRepository, times(0)).save(any(Payment.class));
  }

  @Test
  void testSetStatusIfValid() {
    Payment payment = payments.get(0);
    Payment result = new Payment(payment.getId(), payment.getMethod(), 
      PaymentStatus.SUCCESS.getValue(),mapVC);
    doReturn(order).when(paymentOrders).get(anyString());
    paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
    assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
  }

  @Test
  void testGetPaymentIfValid() {
    Payment payment = payments.get(1);
    doReturn(payment).when(paymentRepository).findById(payment.getId());
    Payment result = paymentService.getPayment(payment.getId());
    assertEquals(payment.getId(), result.getId());
  }

  @Test
  void testGetPaymentIfInvalid() {
    doReturn(null).when(paymentRepository).findById("zczc");
    assertNull(paymentService.getPayment("zczc"));
  }

  @Test
  void testGetAllPayments() {
    doReturn(payments).when(paymentRepository).getAll();
    List<Payment> result = paymentService.getAllPayments();
    assertEquals(2, result.size());
  }
}
