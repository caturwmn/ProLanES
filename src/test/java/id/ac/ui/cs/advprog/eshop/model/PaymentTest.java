package id.ac.ui.cs.advprog.eshop.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {
  private List<Payment> payments;
  private Map<String,String> map;

  @BeforeEach
  void setUp() {
    this.payments = new ArrayList<>();
    map = new HashMap<>();
    map.put("voucherCode", "ESHOP192745ADA12");
    
    Payment payment1 = new Payment();
    payment1.setId("1h37rou1-aljbfu314-8y2bo480");
    payment1.setMethod("Voucher Code");
    payment1.setStatus("WAITING");
    payment1.setPaymentData(map);
    Payment payment2 = new Payment();
    payment1.setId("1301vbv9-ano81v371-la83qbo8");
    payment1.setMethod("Voucher Code");
    payment1.setStatus("WAITING");
    payment1.setPaymentData(map);
    payments.add(payment1);
    payments.add(payment2);
  }

  @Test
  void testCreatePaymentDefaultStatus() {
    Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
      "Voucher Code", map);

    assertEquals("1301vbv9-ano81v371-la83qbo8", payment.getId());
    assertEquals("Voucher Code", payment.getMethod());
    assertEquals("WAITING", payment.getStatus());
    assertEquals(map, payment.getPaymentData());
  }

  @Test
  void testCreatePaymentInvalidStatus() {
    assertThrows(IllegalArgumentException.class, () -> {
      Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", "Argh"
        "Voucher Code", map);
    });
  }

  @Test
  void testCreatePaymentInvalidMethod() {
    assertThrows(IllegalArgumentException.class, () -> {
      Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
        "Monopoly Cash", map);
    });
  }

  @Test
  void testSetStatusPaymentToSuccess() {
    Payment payment = payments.get(1);
    payment.setStatus("SUCCESS");
    assertEquals("SUCCESS", payment.getStatus());
  }

  @Test
  void testSetStatusPaymentToInvalid() {
    Payment payment = payments.get(0);
    
    assertThrows(IllegalArgumentException.class, 
      () -> {payment.setStatus("Priority");
    });
  }
}
