package io.bayrktlihn.sec11;

import io.bayrktlihn.courseutil.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class Lec03SinkThreadSafety {

  public static void main(String[] args) {
    //    handle though which we would push items
    final Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

//    handle through subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    final List<Object> list = new ArrayList<>();

    flux.subscribe(list::add);

//    for (int i = 0; i < 1000; i++) {
//      final int j = i;
//      CompletableFuture.runAsync(() -> {
//        sink.tryEmitNext(j);
//      });
//    }

    for (int i = 0; i < 1000; i++) {
      final int j = i;
      CompletableFuture.runAsync(() -> {
        sink.emitNext(j, (s, e) -> true);
      });
    }

    Util.sleepSeconds(3);
    System.out.println(list.size());
  }
}
