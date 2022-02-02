package io.bayrktlihn.sec01;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec08MonoFromRunnable {

  public static void main(String[] args) {

    Mono.fromRunnable(Lec08MonoFromRunnable::timeConsumingProcess)
        .subscribe(
            Util.onNext(),
            Util.onError(),
            () -> {
              System.out.println("process is done. Sending emails...");
            }
        );
  }

  private static void timeConsumingProcess() {
    Util.sleepSeconds(3);
    System.out.println("Operation completed");
  }
}
