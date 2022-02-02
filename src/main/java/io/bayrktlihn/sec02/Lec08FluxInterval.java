package io.bayrktlihn.sec02;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class Lec08FluxInterval {

  public static void main(String[] args) {
    Flux.interval(Duration.ofSeconds(1))
        .subscribe(Util.onNext());

    Util.sleepSeconds(5);
  }
}
