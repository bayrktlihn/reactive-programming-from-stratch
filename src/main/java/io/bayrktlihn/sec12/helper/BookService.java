package io.bayrktlihn.sec12.helper;

import io.bayrktlihn.courseutil.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class BookService {

  private static final Map<String, Integer> MAP = new HashMap<>();

  static {
    MAP.put("std", 2);
    MAP.put("prime", 3);
  }

  public static Mono<String> getBook() {
    return Mono.deferContextual(ctx -> {
          if (ctx.get("allow")) {
            return Mono.just(Util.faker().book().title());
          } else {
            return Mono.error(new RuntimeException("not-allowed"));
          }
        })
        .contextWrite(rateLimiterContext());
  }

  private static Function<Context, Context> rateLimiterContext() {
    return context -> {
      if (context.hasKey("category")) {
        final String category = context.get("category").toString();
        final Integer attempts = MAP.get(category);
        if (attempts > 0) {
          MAP.put(category, attempts - 1);
          return context.put("allow", true);
        }
      }
      return context.put("allow", false);
    };
  }
}
