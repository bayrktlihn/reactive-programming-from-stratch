package io.bayrktlihn.sec02;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec09FluxFromMono {

  public static void main(String[] args) {

//    final Mono<String> mono = Mono.just("1");
//
//    final Flux<String> flux = Flux.from(mono);
//
//    flux.subscribe(Util.onNext());

    Flux.range(1, 10)
        .next() // 1
        .filter(i -> i > 3)
        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
  }

  private static void doSomething(final Flux<String> flux) {

  }
}
