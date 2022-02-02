package io.bayrktlihn.sec02;

import io.bayrktlihn.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec03FromArrayOrList {

  public static void main(String[] args) {
//    final List<String> strings = Arrays.asList("a", "b", "c");
//    Flux.fromIterable(strings)
//        .subscribe(Util.onNext());

    final Integer[] arr = {2, 5, 7, 8};

    Flux.fromArray(arr)
        .subscribe(Util.onNext());

  }
}
