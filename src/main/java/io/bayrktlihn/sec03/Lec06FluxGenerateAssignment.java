package io.bayrktlihn.sec03;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerateAssignment {

  public static void main(String[] args) {

//    canada
//    max = 10
//    subscriber cancels - exit

    Flux.generate(synchronousSink -> {
          final String country = Util.faker().country().name();
          System.out.println("emitting " + country);
          synchronousSink.next(country);
          if (country.equalsIgnoreCase("canada")) {
            synchronousSink.complete();
          }
        })
        .subscribe(Util.subscriber());
  }
}
