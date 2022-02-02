package io.bayrktlihn.sec09.assignment;

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
  private String category;

  private PurchaseOrder() {
    item = Util.faker().commerce().productName();
    price = Double.parseDouble(Util.faker().commerce().price());
    category = Util.faker().commerce().department();
  }

  public static PurchaseOrder createRandomPurchaseOrder() {
    return new PurchaseOrder();
  }
}
