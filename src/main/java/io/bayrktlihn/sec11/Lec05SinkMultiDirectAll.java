package io.bayrktlihn.sec11;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class Lec05SinkMultiDirectAll {

  public static void main(String[] args) {

    System.setProperty("reactor.bufferSize.small", "16");

//    handle though which we would push items
    final Many<Object> sink = Sinks.many().multicast().directBestEffort();

//    handle through subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    flux.subscribe(Util.subscriber("sam"));
    flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

    for (int i = 0; i < 100; i++) {
      sink.tryEmitNext(i);
    }

    Util.sleepSeconds(10);
  }
}
