package io.bayrktlihn.sec04;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

  public static void main(String[] args) {
    Flux.range(1, 20)
        .handle((integer, synchronousSink) -> {
          if (integer == 7) {
            synchronousSink.complete();
          } else {
            synchronousSink.next(integer);
          }
        })
        .subscribe(Util.subscriber());

  }
}
