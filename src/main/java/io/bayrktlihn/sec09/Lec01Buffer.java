package io.bayrktlihn.sec09;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class Lec01Buffer {

  public static void main(String[] args) {
    eventStream()
        .bufferTimeout(5, Duration.ofSeconds(2))
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);
  }

  private static Flux<String> eventStream() {
    return Flux.interval(Duration.ofMillis(300))
        .map(i -> "event" + i);
  }
}
