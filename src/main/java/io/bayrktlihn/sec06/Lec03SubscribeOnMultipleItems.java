package io.bayrktlihn.sec06;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03SubscribeOnMultipleItems {


  public static void main(String[] args) {
    final Flux<Object> flux = Flux.create(fluxSink -> {
          printThreadName("create");
          for (int i = 0; i < 20; i++) {
            fluxSink.next(i);
            Util.sleepSeconds(1);
          }
          fluxSink.complete();
        })
        .doOnNext(i -> printThreadName("next " + i));

    flux
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(v -> printThreadName("sub " + v));

    flux
        .subscribeOn(Schedulers.parallel())
        .subscribe(v -> printThreadName("sub " + v));

    Util.sleepSeconds(5);


  }

  private static void printThreadName(final String msg) {
    System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
  }

}
