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
  private Map<String,String> map;

  @BeforeEach
  void setUp() {
    map = new HashMap<>();
    map.put("voucherCode", "ESHOP192745ADA12");
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
      Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
        "Cash On Destination", "Argh", map);
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
    Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
     "Voucher Code", "Argh", map);
    payment.setStatus("SUCCESS");
    assertEquals("SUCCESS", payment.getStatus());
  }

  @Test
  void testSetStatusPaymentToInvalid() {
    Payment payment = new Payment("1301vbv9-ano81v371-la83qbo8", 
      "Voucher Code", map);
    
    assertThrows(IllegalArgumentException.class, 
      () -> {payment.setStatus("Priority");
    });
  }
}
