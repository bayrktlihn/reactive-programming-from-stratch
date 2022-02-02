package io.bayrktlihn.sec09;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec09.assignment.OrderProcessor;
import io.bayrktlihn.sec09.assignment.OrderService;
import io.bayrktlihn.sec09.assignment.PurchaseOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import reactor.core.publisher.Flux;

public class Lec06Assignment {

  public static void main(String[] args) {
    final Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = new HashMap<>();
    map.put("Kids", OrderProcessor.kidsProcessing());
    map.put("Automotive", OrderProcessor.automotiveProcessing());

    final Set<String> set = map.keySet();

    OrderService.orderStream()
        .filter(p -> set.contains(p.getCategory()))
        .groupBy(PurchaseOrder::getCategory) // 2 keys
        .flatMap(gf -> map.get(gf.key()).apply(gf)) // flux
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);
  }
}
