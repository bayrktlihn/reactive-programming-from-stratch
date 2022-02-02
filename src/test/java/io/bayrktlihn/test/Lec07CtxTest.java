package io.bayrktlihn.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec07CtxTest {

  @Test
  public void test1() {
    StepVerifier.create(getWelcomeMessage())
        .verifyError(RuntimeException.class);
  }

  @Test
  public void test2() {
    final StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));

    StepVerifier.create(getWelcomeMessage(), options)
        .expectNext("Welcome sam")
        .verifyComplete();
  }

  private Mono<String> getWelcomeMessage() {
    return Mono.deferContextual(contextView -> {
      if (contextView.hasKey("user")) {
        return Mono.just("Welcome " + contextView.get("user"));
      } else {
        return Mono.error(new RuntimeException("unauthenticated"));
      }
    });
  }
}
