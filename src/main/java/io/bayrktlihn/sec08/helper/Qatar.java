package io.bayrktlihn.sec08.helper;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class Qatar {

  public static Flux<String> getFlights() {
    return Flux.range(1, Util.faker().random().nextInt(1, 5))
        .delayElements(Duration.ofSeconds(1))
        .map(i -> "Qatar " + Util.faker().random().nextInt(100, 999))
        .filter(i -> Util.faker().random().nextBoolean());
  }
}
