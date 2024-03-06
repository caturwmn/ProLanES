package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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
    this.status = "WAITING";
    this.paymentData = paymentData;

    List<String> methods = new ArrayList<>();
    methods.add("Voucher Code");
    methods.add("Cash On Delivery");
        
    if (methods.contains(method)) {
      this.method = "Voucher Code";
    } else {
      throw new IllegalArgumentException();
    }
  }

  public Payment(String id, String method, String status, Map<String,String> paymentData) {
    this(id, method, paymentData);

    List<String> statuses = new ArrayList<>();
    statuses.add("WAITING");
    statuses.add("SUCCESS");
    statuses.add("REJECTED");

    if (statuses.contains(status)) {
      this.status = status;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public void setStatus(String status) {
    List<String> statuses = new ArrayList<>();
    statuses.add("WAITING");
    statuses.add("SUCCESS");
    statuses.add("REJECTED");

    if(statuses.contains(status)) {
      this.status = status;
    } else {
      throw new IllegalArgumentException();
    }
  }
}
