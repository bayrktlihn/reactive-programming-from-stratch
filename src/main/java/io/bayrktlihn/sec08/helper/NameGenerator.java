package io.bayrktlihn.sec08.helper;

import io.bayrktlihn.courseutil.Util;
import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Flux;

public class NameGenerator {

  private List<String> list = new ArrayList<>();

  public Flux<String> generateNames() {
    return Flux.generate(synchronousSink -> {
          System.out.println("generated fresh");
          Util.sleepSeconds(1);
          final String name = Util.faker().name().firstName();
          list.add(name);
          synchronousSink.next(name);
        })
        .cast(String.class)
        .startWith(getFromCache());
  }

  private Flux<String> getFromCache() {
    return Flux.fromIterable(list);
  }
}
