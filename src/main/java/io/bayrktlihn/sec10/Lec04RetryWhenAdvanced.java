package io.bayrktlihn.sec10;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class Lec04RetryWhenAdvanced {

  public static void main(String[] args) {
    orderService(Util.faker().business().creditCardNumber())
        .retryWhen(Retry.from(retrySignalFlux ->
                                  retrySignalFlux.doOnNext(retrySignal -> {
                                        System.out.println(retrySignal.totalRetries());
                                        System.out.println(retrySignal.failure());
                                      })
                                      .handle((retrySignal, synchronousSink) -> {
                                        if (retrySignal.failure().getMessage().equals("500")) {
                                          synchronousSink.next(1);
                                        } else {
                                          synchronousSink.error(retrySignal.failure());
                                        }
                                      })
                                      .delayElements(Duration.ofSeconds(1))
        ))
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);
  }

  //  order service
  private static Mono<String> orderService(final String ccNumber) {
    return Mono.fromSupplier(() -> {
      processPayment(ccNumber);
      return Util.faker().idNumber().valid();
    });
  }


  //  payment service
  private static void processPayment(final String ccNumber) {
    final int random = Util.faker().random().nextInt(1, 10);
    if (random < 8) {
      throw new RuntimeException("500");
    } else if (random < 10) {
      throw new RuntimeException("404");
    }
  }

}
