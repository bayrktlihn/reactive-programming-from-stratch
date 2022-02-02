package io.bayrktlihn.sec02.helper;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class NameGenerator {

//  public static List<String> getNames(final int count) {
//    final List<String> list = new ArrayList<>(count);
//    for (int i = 0; i < count; i++) {
//      list.add(getName());
//    }
//    return list;
//  }

  public static Flux<String> getNames(final int count) {
    return Flux.range(0, count)
        .map(i -> getName());
  }

  private static String getName() {
    Util.sleepSeconds(1);
    return Util.faker().name().fullName();
  }

}