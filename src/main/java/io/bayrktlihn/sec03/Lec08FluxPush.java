package io.bayrktlihn.sec03;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

public class Lec08FluxPush {

  public static void main(String[] args) {

    final NameProducer nameProducer = new NameProducer();

    Flux.push(nameProducer)
        .subscribe(Util.subscriber());

    final Runnable runnable = nameProducer::produce;

    for (int i = 0; i < 10; i++) {
      new Thread(runnable).start();
    }

    Util.sleepSeconds(2);
  }
}