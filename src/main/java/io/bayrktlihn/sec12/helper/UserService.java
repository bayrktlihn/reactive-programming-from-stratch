package io.bayrktlihn.sec12.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import reactor.util.context.Context;

public class UserService {

  private static final Map<String, String> MAP;

  static {
    MAP = new HashMap<>();
    MAP.put("sam", "std");
    MAP.put("mike", "prime");
  }

  public static Function<Context, Context> userCategoryContext() {
    return ctx -> {
      final String user = ctx.get("user").toString();
      final String category = MAP.get(user);
      return ctx.put("category", category);
    };
  }
}
