package io.bayrktlihn.sec01;

import io.bayrktlihn.courseutil.Util;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {

  public static void main(String[] args) {

    // use just only when you have data already
//    final Mono<String> mono = Mono.just(getName());

    final Supplier<String> stringSupplier = Lec05MonoFromSupplier::getName;
    final Mono<String> mono = Mono.fromSupplier(stringSupplier);
    mono.subscribe(
        Util.onNext()
    );

    final Callable<String> stringCallable = Lec05MonoFromSupplier::getName;
    Mono.fromCallable(stringCallable).subscribe(
        Util.onNext()
    );

  }

  private static String getName() {
    System.out.println("Generating name..");
    return Util.faker().name().fullName();
  }
}
