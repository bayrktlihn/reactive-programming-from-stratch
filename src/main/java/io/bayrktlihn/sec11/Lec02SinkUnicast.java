package io.bayrktlihn.sec11;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class Lec02SinkUnicast {

  public static void main(String[] args) {
//    handle though which we would push items
    final Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

//    handle through subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    flux.subscribe(Util.subscriber("sam"));
    flux.subscribe(Util.subscriber("mike"));

    sink.tryEmitNext("hi");
    sink.tryEmitNext("how are you");
    sink.tryEmitNext("?");
  }
}
