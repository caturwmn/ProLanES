package id.ac.ui.cs.advprog.eshop.model;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

class PaymentTest {
  private Map<String,String> map;

  @BeforeEach
  void setUp() {
    map = new HashMap<>();
    map.put("voucherCode", "ESHOP192745ADA12");
  }

  @Test
  void testCreatePaymentDefaultStatus() {
    Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
      PaymentMethods.VC.getValue(), map);

    assertEquals("1301vbv9-ano81v371-la83qbo8", payment.getId());
    assertEquals(PaymentMethods.VC.getValue(), payment.getMethod());
    assertEquals(PaymentStatus.WAITING.getValue(), payment.getStatus());
    assertEquals(map, payment.getPaymentData());
  }

  @SuppressWarnings("unused")
  @Test
  void testCreatePaymentInvalidStatus() {
    assertThrows(IllegalArgumentException.class, () -> {
      Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
        PaymentMethods.COD.getValue(), "Argh", map);
    });
  }

  @SuppressWarnings("unused")
  @Test
  void testCreatePaymentInvalidMethod() {
    assertThrows(IllegalArgumentException.class, () -> {
      Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
        "Monopoly Cash", map);
    });
  }

  @Test
  void testSetStatusPaymentToSuccess() {
    Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
     PaymentMethods.VC.getValue(), map);
    payment.setStatus(PaymentStatus.SUCCESS.getValue());
    assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
  }

  @Test
  void testSetStatusPaymentToInvalid() {
    Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
      PaymentMethods.VC.getValue(), map);
    
    assertThrows(IllegalArgumentException.class, 
      () -> {payment.setStatus("Priority");
    });
  }
}
