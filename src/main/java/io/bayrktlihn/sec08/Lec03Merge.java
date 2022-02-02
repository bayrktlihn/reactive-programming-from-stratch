package io.bayrktlihn.sec08;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec08.helper.AmericanAirlines;
import io.bayrktlihn.sec08.helper.Emirates;
import io.bayrktlihn.sec08.helper.Qatar;
import reactor.core.publisher.Flux;

public class Lec03Merge {

  public static void main(String[] args) {
    final Flux<String> merge = Flux.merge(
        Qatar.getFlights(),
        Emirates.getFlights(),
        AmericanAirlines.getFlights()
    );

    merge.subscribe(Util.subscriber());

    Util.sleepSeconds(10);
  }
}
