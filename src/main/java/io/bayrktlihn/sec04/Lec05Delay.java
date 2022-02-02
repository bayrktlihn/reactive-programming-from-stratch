package io.bayrktlihn.sec04;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class Lec05Delay {

  public static void main(String[] args) {

    System.setProperty("reactor.bufferSize.x", "9");

    Flux.range(1, 100) // 32
        .log()
        .delayElements(Duration.ofSeconds(1))
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);
  }
}
