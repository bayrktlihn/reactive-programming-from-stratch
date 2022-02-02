package io.bayrktlihn.sec04.helper;

import io.bayrktlihn.courseutil.Util;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseOrder {

  private String item;
  private double price;
  private int userId;

  private PurchaseOrder(final int userId) {
    this.userId = userId;
    this.item = Util.faker().commerce().productName();
    this.price = Double.parseDouble(Util.faker().commerce().price());
  }

  public static PurchaseOrder createRandomPurchaseOrder(final int userId) {
    return new PurchaseOrder(userId);
  }
}
