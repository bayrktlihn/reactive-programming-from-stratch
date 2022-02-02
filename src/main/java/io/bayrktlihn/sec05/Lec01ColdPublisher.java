package io.bayrktlihn.sec05;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

public class Lec01ColdPublisher {

  public static void main(String[] args) {

    final Flux<String> movieStream = Flux.fromStream(Lec01ColdPublisher::getMovie)
        .delayElements(Duration.ofSeconds(2));

    movieStream
        .subscribe(Util.subscriber("sam"));

    Util.sleepSeconds(5);

    movieStream
        .subscribe(Util.subscriber("mike"));

    Util.sleepSeconds(60);

  }

  private static Stream<String> getMovie() {
    System.out.println("Got the movie streaming req");
    return Stream.of(
        "Scene 1",
        "Scene 2",
        "Scene 3",
        "Scene 4",
        "Scene 5",
        "Scene 6",
        "Scene 7"
    );
  }
}
