package io.bayrktlihn.sec02;

import io.bayrktlihn.courseutil.Util;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Lec06Subscription {

  public static void main(String[] args) {
    final AtomicReference<Subscription> atomicReference = new AtomicReference<>();
    Flux.range(1, 20)
        .log()
        .subscribeWith(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(final Subscription s) {
            System.out.println("Received Sub : " + s);
            atomicReference.set(s);
          }

          @Override
          public void onNext(final Integer integer) {
            System.out.println("onNext : " + integer);
          }

          @Override
          public void onError(final Throwable t) {
            System.out.println("onError : " + t.getMessage());
          }

          @Override
          public void onComplete() {
            System.out.println("onComplete");
          }
        });

    Util.sleepSeconds(3);
    atomicReference.get().request(3);
    Util.sleepSeconds(5);
    atomicReference.get().request(3);
    Util.sleepSeconds(5);
    System.out.println("going to cancel");
    atomicReference.get().cancel();
    Util.sleepSeconds(3);
    atomicReference.get().request(4);

    Util.sleepSeconds(3);
  }
}
