package io.bayrktlihn.sec05;

import io.bayrktlihn.courseutil.Util;
import java.time.Duration;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

public class Lec04HotPublishAutoConnect {

  public static void main(String[] args) {
//    share = publish().refCount(1)
    final Flux<String> movieStream = Flux.fromStream(Lec04HotPublishAutoConnect::getMovie)
        .delayElements(Duration.ofSeconds(1))
        .publish()
        .autoConnect(0);

    Util.sleepSeconds(3);

    movieStream
        .subscribe(Util.subscriber("sam"));

    Util.sleepSeconds(10);

    System.out.println("Mike is about to join");

    movieStream
        .subscribe(Util.subscriber("mike"));

    Util.sleepSeconds(60);

  }

  //  movie-theatre
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
