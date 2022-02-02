package io.bayrktlihn.sec04.helper;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class OrderService {

  private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

  static {
    final List<PurchaseOrder> list1 = Arrays.asList(
        PurchaseOrder.createRandomPurchaseOrder(1),
        PurchaseOrder.createRandomPurchaseOrder(1),
        PurchaseOrder.createRandomPurchaseOrder(1)
    );

    final List<PurchaseOrder> list2 = Arrays.asList(
        PurchaseOrder.createRandomPurchaseOrder(2),
        PurchaseOrder.createRandomPurchaseOrder(2),
        PurchaseOrder.createRandomPurchaseOrder(2)
    );

    db.put(1, list1);
    db.put(2, list2);

  }

  public static Flux<PurchaseOrder> getOrders(final int userId) {
    return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
          db.get(userId).forEach(purchaseOrderFluxSink::next);
          purchaseOrderFluxSink.complete();
        })
        .delayElements(Duration.ofSeconds(1));
  }
}
