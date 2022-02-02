package io.bayrktlihn.sec01;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06SupplierRefactoring {

  public static void main(String[] args) {
    getName();
    final String name = getName()
        .subscribeOn(Schedulers.boundedElastic())
        .block();
    System.out.println(name);
    getName();

    Util.sleepSeconds(4);
  }

  private static Mono<String> getName() {
    System.out.println("entered getName method");
    return Mono.fromSupplier(() -> {
      System.out.println("Genearting name..");
      Util.sleepSeconds(3);
      return Util.faker().name().fullName();
    }).map(String::toUpperCase);
  }
}
