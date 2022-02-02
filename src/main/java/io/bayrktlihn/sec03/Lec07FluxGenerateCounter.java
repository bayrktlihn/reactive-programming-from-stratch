package io.bayrktlihn.sec03;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateCounter {

  public static void main(String[] args) {
    Flux.generate(
            () -> 1,
            (counter, sink) -> {
              final String country = Util.faker().country().name();
              sink.next(country);
              if (counter >= 10 || country.equalsIgnoreCase("canada")) {
                sink.complete();
              }
              return counter + 1;
            }
        )
        .take(4)
        .subscribe(Util.subscriber());
  }
}
