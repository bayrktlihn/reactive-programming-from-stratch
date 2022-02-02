package io.bayrktlihn.sec09;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class Lec05Group {

  public static void main(String[] args) {
    Flux.range(1, 30)
        .delayElements(Duration.ofSeconds(0))
        .groupBy(i -> i % 2) // key 0, 1
        .subscribe(gf -> process(gf, gf.key()));

    Util.sleepSeconds(60);

  }

  private static void process(final Flux<Integer> flux, final int key) {
    System.out.println("Called");
    flux.subscribe(i -> System.out.println("Key : " + key + ", Item : " + i));
  }
}
