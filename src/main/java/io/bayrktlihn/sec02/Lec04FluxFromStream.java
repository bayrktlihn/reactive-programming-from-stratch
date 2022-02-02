package io.bayrktlihn.sec02;

import io.bayrktlihn.courseutil.Util;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

public class Lec04FluxFromStream {

  public static void main(String[] args) {

    final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    final Stream<Integer> stream = list.stream();

//    stream.forEach(System.out::println); // closed
//    stream.forEach(System.out::println);

    final Flux<Integer> integerFlux = Flux.fromStream(list::stream);

    integerFlux.subscribe(
        Util.onNext(),
        Util.onError(),
        Util.onComplete()
    );

    integerFlux.subscribe(
        Util.onNext(),
        Util.onError(),
        Util.onComplete()
    );
  }
}
