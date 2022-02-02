package io.bayrktlihn.sec04;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class Lec07Timeout {

  public static void main(String[] args) {
    getOrderNumbers()
        .timeout(Duration.ofSeconds(2), fallback())
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);
  }

  private static Flux<Integer> getOrderNumbers() {
    return Flux.range(1, 10)
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Integer> fallback() {
    return Flux.range(100, 10)
        .delayElements(Duration.ofMillis(200));
  }
}
