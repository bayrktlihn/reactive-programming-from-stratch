package io.bayrktlihn.sec01;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Lec01Stream {

  public static void main(String[] args) {
    final Stream<Integer> stream = Stream.of(1).map(i -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return i * 2;
    });

//    System.out.println(stream);
    stream.forEach(System.out::println);
  }
}
