package io.bayrktlihn.sec08;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {

  public static void main(String[] args) {
    final Flux<String> flux1 = Flux.just("a", "b");
    final Flux<String> flux2 = Flux.just("c", "d", "e");
    final Flux<String> flux3 = Flux.error(new RuntimeException("oops"));

    final Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);

    flux.subscribe(Util.subscriber());
  }
}
