package io.bayrktlihn.test;

import io.bayrktlihn.sec09.helper.BookOrder;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec04AssertTest {

  @Test
  public void test1() {

    final Mono<BookOrder> mono = Mono.fromSupplier(() -> BookOrder.createRandomBookOrder());

    StepVerifier.create(mono)
        .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
        .verifyComplete();
  }

  @Test
  public void test2() {

    final Mono<BookOrder> mono = Mono.fromSupplier(() -> BookOrder.createRandomBookOrder())
        .delayElement(Duration.ofSeconds(3));

    StepVerifier.create(mono)
        .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
        .expectComplete()
        .verify(Duration.ofSeconds(4));
  }
}
