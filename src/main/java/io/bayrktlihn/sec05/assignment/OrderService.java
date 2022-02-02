package io.bayrktlihn.sec05.assignment;

import java.time.Duration;
import java.util.Objects;
import reactor.core.publisher.Flux;

public class OrderService {

  private Flux<PurchaseOrder> flux;

  public Flux<PurchaseOrder> orderStream() {
    if (Objects.isNull(flux)) {
      flux = getOrderStream();
    }
    return flux;
  }

  public Flux<PurchaseOrder> getOrderStream() {
    return Flux.interval(Duration.ofMillis(100))
        .map(i -> PurchaseOrder.createRandomPurchaseOrder())
        .publish()
        .refCount(2);
  }
}
