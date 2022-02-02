package io.bayrktlihn.sec01;

import io.bayrktlihn.courseutil.Util;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Mono;

public class Lec07MonoFromFuture {

  public static void main(String[] args) {

    Mono.fromFuture(getName())
        .subscribe(Util.onNext());

    Util.sleepSeconds(1);
  }

  private static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
  }
}
