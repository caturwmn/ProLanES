package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethods {
  VC("Voucher Code"),
  COD("Cash On Destination");

  private final String value;
  private PaymentMethods(String value) {
    this.value = value;
  }

  public static boolean contains(String param) {
    for (PaymentMethods paymentMethods : PaymentMethods.values()) {
      if (paymentMethods.getValue().equals(param)) {
        return true;
      }
    }
    return false;
  }
}
