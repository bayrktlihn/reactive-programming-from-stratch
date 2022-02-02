package io.bayrktlihn.sec06;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PubSubOn {


  public static void main(String[] args) {
    final Flux<Object> flux = Flux.create(fluxSink -> {
          printThreadName("create");
          for (int i = 0; i < 4; i++) {
            fluxSink.next(i);
          }
          fluxSink.complete();
        })
        .doOnNext(i -> printThreadName("next " + i));

    flux
        .publishOn(Schedulers.parallel())
        .doOnNext(i -> printThreadName("next " + i))
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(v -> printThreadName("sub " + v));

    Util.sleepSeconds(5);


  }

  private static void printThreadName(final String msg) {
    System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
  }

}
