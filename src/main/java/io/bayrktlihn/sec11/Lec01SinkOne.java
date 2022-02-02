package io.bayrktlihn.sec11;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.One;

public class Lec01SinkOne {

  public static void main(String[] args) {

//    mono 1 value / empty / error
    final One<Object> sink = Sinks.one();

    final Mono<Object> mono = sink.asMono();

    mono.subscribe(Util.subscriber("sam"));
    mono.subscribe(Util.subscriber("mike"));

/*    sink.emitValue("hi", (signalType, emitResult) -> {
      System.out.println(signalType.name());
      System.out.println(emitResult.name());
      return false;
    });

    sink.emitValue("hello", (signalType, emitResult) -> {
      System.out.println(signalType.name());
      System.out.println(emitResult.name());
      return true;
    });*/

    sink.tryEmitValue("hello");

  }
}
