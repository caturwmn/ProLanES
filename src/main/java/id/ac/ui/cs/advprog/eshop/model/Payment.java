package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

@Builder
@Getter
@Setter
public class Payment {
  String id;
  String method;
  String status;
  Map<String,String> paymentData;

  public Payment(String id, String method, Map<String,String> paymentData) {
    this.id = id;
    this.status = PaymentStatus.WAITING.getValue();
    this.paymentData = paymentData;
        
    if (PaymentMethods.contains(method)) {
      this.method = method;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public Payment(String id, String method, String status, Map<String,String> paymentData) {
    this(id, method, paymentData);

    if (PaymentStatus.contains(status)) {
      this.status = status;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public void setStatus(String status) {
    if(PaymentStatus.contains(status)) {
      this.status = status;
    } else {
      throw new IllegalArgumentException();
    }
  }
}
